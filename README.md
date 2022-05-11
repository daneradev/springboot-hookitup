# Introduction:
It is a microservice-based application developed with Spring boot (Figure 1). The app is essentially  developed to serve  2 fronted applications:

**Producers:** They can post a product or service and take or leave an offer.

**Consumers:** They can look for a particular product or service and then make a deal or make an offer.

<img
  src="/images/arquitecture.jpg"
  alt="Alt text"
  title="Application Arquitecture"
  style="display: inline-block; margin: 0 auto; max-width: 300px">
Figure 1: Application Arquitecture

# Arquitecture:

The application has the following microservices:

1. **Config-Server:** This service helps us to keep the services configuration information separate from the physical deployment, centralize the application configuration, make the application configuration highly available and redundant. I used Github as a repository and I generated key pairs with keytool to encrypt and decrypt asymmetrically passwords and usernames.

2. ** Eureka-server:** It is a great solution for quickly scaling horizontally the service instances running in an environment. This service discovery works in a cluster with multiple instances(nodes) where each node has an identical configuration, works together with others nodes and shares the state. When a service comes online it registers its IP with the discovery agent and it starts sending  heartbeats to the service discovery agent so when it starts failing to return a good health check it is removed from the pool of available service instances.

3.  **Gateway-server:**  It puts all service calls behind a single URL and maps those calls using eureka-server to their actual service instances. It performs a role of client OAuht2 so if the incoming request is unauthorized the gateway-server will walk it through a login process where you can choose to go as consumer or producer depending in your choice some scopes will be granted to you, then if the login process was successful the client will get a JWT that has to be send in every request so the gateway-server gets it and  sends a POST request to Keycloak in order to validate it, if the JWT is not valid or has expired the request will get a 401 http response. In the other scenario where the JWT is valid the request may get the resource only if it has the authorities to do otherwise the request will get a 403 http response.

4. **Producer**, **Consumer**, **Deal** and **Rating** services:  These services expose some Rest apis for producer and consumers clients. Producer, Consumer and Deal services are connected to a Cassandra Database and rating service to Postgres also they are  bound to Kafka where they publish and are subscribed to some topics. These services act as a resource server, it means that the service gets a JWT from the gateway-server, then spring security will try to validate it with a public key taken from issuer-uri set in the application properties. Each service exposes an actuator/prometheus endpoint to be measured with Grafana.


# How does the application work?

Let's break down the application into Producer and Consumer client, let’s start with Producer.

Before I start, it is important to highlight that I had to take a query-driven approach to model most of the backend applications given that I chose Cassandra as my primary database so I started designing the frontend for both producer and consumer client.

## Producer:

1. In screen 1(figure 2) the producer can save a product or service, when the post is submited it sends a POST request to the producer-service then the product will be persisted in the Cassandra Database.

2. In screen 2(figure 2) the producer can look for all the posts that they have active. Each post has a delete button in order to send a DELETE request to the producer service,  in case some post has an offer, an offer button will show up.

3. In screen 3(figure 2) the producer appreciates all the offers for a specific post. If they press the not-interested-button it will send a POST request to our producer-service where the number of offers for the specified post will be updated (-1) then the producer-service will publish a message in the declined–offer-topic in  kafka then the consumer-service gets the message and proceed to delete the offer related to the post, after that a new declined offer record will be saved in Cassandra database. In the other case you press the get-it-button, it will send a DELETE request to the producer-service to delete that post and publish a message in deal-started-by-producer-topic following that, the consumer-service will get the message and  it will proceed to delete every offer related to that specific post then it will publish a message in the close-deal-topic then the deal-service will get the message where new records will be persited in a Cassandra database about the deal made, following this, our deal-service will publish a message in the pending-rating-topic where the rating-service gets the message and create a pending rating record for both producer and consumer client.

<img
  src="/images/figure2.jpg"
  alt="Alt text"
  title="Application Arquitecture"
  style="display: inline-block; margin: 0 auto">
Figure 2: Producer's screens 1, 2, 3.

4. In screen 4(figure 3) the producer will be able to get all deals made before by making a GET request to the deal-service.

5. In screen 5(figure 3)  the producer has all their pending ratings, pending ratings are only created when producer and consumer close a deal. You have 2 option, first producer can skip it that will send a DELETE request to the rating-service in order to delete that specific pending rating and second the producer can evaluate the consumer from 0 to 5 and type some comment about it that will send a POST request to the rating-service to delete the pending rating, update the consumer rating and add a new record to the consumer comments.

6. In screen 6(figure 3) the producer notices their current rating and all the ratings and comments which consumers have typed.

7. The last screen is about getting all the deals which went wrong and could not be closed by sending a GET request to the deal-service

<img
  src="/images/figure3.jpg"
  alt="Alt text"
  title="Application Arquitecture"
  style="display: inline-block; margin: 0 auto">
Figure 3: Producer's screens 4, 5, 6.

## Consumer:

1. In screen 1(figure 4) the consumer can see all their active offers made by sending a GET request to the consumer service. For each offer you have a button to delete it by sending a DELETE request to the consumer-service, then after deleting the records it will publish a message in subtract-number-offer-topic, when the producer service gets that message it will update the number of offers for that specific post.

2. In screen 2(figure 4) the consumer have available all the active posts by sending a GET request to the producer-service. For every retrieved post  the consumer have 2 options, the first is to make a deal so it will delete all the offers related to that post, then it will publish a message in deal-started-by-consumer-topic, following that  the producer-service will receive the message and proceed to delete the post and publish a message in close-deal-topic, after that the deal-service will get a message and create a record for both producer and consumer client about the deal made,  then it will publish a message in pending-rating-topic where a pending rating will be saved in the postgres database for both producer and consumer. For the second option the consumer makes an offer.

3. In screen 3(figure 4) the consumer can make an offer for a specific post by sending a POST request to the consumer-service in order to persist the offer in Cassandra database, after that it will publish a message in sum-number-offer-topic when the producer-service gets the message it will update the post by summing 1 to the number of offers.

4. In screen 4(figure 4) the consumer can obtain all their declined offers by sending a GET request to the consumer-service.

<img
  src="/images/figure4.jpg"
  alt="Alt text"
  title="Application Arquitecture"
  style="display: inline-block; margin: 0 auto">
Figure 4: Consumer's screens 1, 2, 3, 4.

5. In screen 5(figure 5) the consumer will be able to get all the deals made before by sending a GET request to the deal-service.

6. In screen 6(figure 5)  the consumer has all their pending ratings. the consumer has 2 option, first the consumer can skip it, that will send a DELETE request to the rating-service in order to delete that specific pending rating and second the consumer can evaluate the producer from 0 to 5 and type some comment about it,  that will send a POST request to the rating-service to delete the pending rating, update the producer rating and add a new record to the producer comments.

7. In screen 7(figure 5) the consumer can visualize their current rating and each ratings and comments published by the producer.

8. The last screen is about getting all the deals which went wrong and could not be closed by sending a GET request to the deal-service.

<img
  src="/images/figure5.jpg"
  alt="Alt text"
  title="Application Arquitecture"
  style="display: inline-block; margin: 0 auto">
Figure 5: Consumer's screens 5, 6, 7.

# References:

1. Carnell, J., Huaylupo, I. (2021) Spring Microservices in Action. 2nd ed. Shelter Island, NY: Manning

2. Carpenter, J., Hewitt, E. (2020) Cassandra The Definitive Guide. 3 ed. Sebastapol: 0'Reilly

3. Scott, D. (2020) Kafka in Action : Manning