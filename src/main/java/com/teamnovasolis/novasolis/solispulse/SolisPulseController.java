package com.teamnovasolis.novasolis.solispulse;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SolisPulseController {

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String greeting(String messages) {
        return "Hello, " + messages + "!";
    }
}
