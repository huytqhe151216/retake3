package com.aladin.huyreport2.service;

import com.aladin.huyreport2.domain.DataRate;
import com.aladin.huyreport2.repository.UserRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    Logger logger = LoggerFactory.getLogger(KafkaListeners.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    private Gson gson = new Gson();
    @KafkaListener(topics = "movie",groupId = "abc")
    void listener(String data){
        logger.info("Consume Movie:", data);
        try {
            DataRate dataRate = gson.fromJson(data, DataRate.class);
            String subject = "Your rate was deleted";
            String content = "Your rate on movie:" +dataRate.getMovieName()
                +"was deleted by admin";
            mailService.sendEmail(dataRate.getEmail(),subject,content,false,false);
        }catch (Exception e){

        }
    }
}
