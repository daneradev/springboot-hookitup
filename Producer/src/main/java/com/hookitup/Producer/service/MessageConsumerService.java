package com.hookitup.Producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

    @Autowired
    private StreamBridge streamBridge;


    public void sendMessage(String binding, String message) {
        streamBridge.send(binding, message);
    }
}
