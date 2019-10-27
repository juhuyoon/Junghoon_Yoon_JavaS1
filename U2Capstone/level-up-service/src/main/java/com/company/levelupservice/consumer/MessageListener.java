package com.company.levelupservice.consumer;

import com.company.levelupservice.LevelUpServiceApplication;
import com.company.levelupservice.controller.LevelUpController;
import com.company.levelupservice.model.LevelViewModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private LevelUpController controller;

        @RabbitListener(queues = LevelUpServiceApplication.QUEUE_NAME)
        public void receiveMessage(LevelViewModel lvm) {
            if (lvm.getLevel_up_id() > 0) {
                controller.updateLevelUp(lvm);
            } else {
                controller.createLevelUp(lvm);
            }
        }
}
