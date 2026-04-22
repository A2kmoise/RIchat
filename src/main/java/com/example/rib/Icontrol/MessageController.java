package com.example.rib.Icontrol;

import org.springframework.web.bind.annotation.*;

@RestController("/richat-api/v1/messages")
public class MessageController {

    @PostMapping("/send-message")
    public void sendMessage(){}

    @GetMapping("/fetch-message")
    public void fetchMessage(){}

    @PutMapping("/update-message")
    public void updateMessage(){}
    @DeleteMapping("/delete-message")
    public void deleteMessage(){}


}
