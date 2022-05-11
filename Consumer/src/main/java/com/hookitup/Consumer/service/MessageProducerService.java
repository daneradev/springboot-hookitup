package com.hookitup.Consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    @Autowired
    private StreamBridge streamBridge;

    public void sendMessage(String binding, String message) {
        streamBridge.send(binding, message);
    }



}
