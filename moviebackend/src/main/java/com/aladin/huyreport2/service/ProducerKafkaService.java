package com.aladin.huyreport2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafkaService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void publish(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
