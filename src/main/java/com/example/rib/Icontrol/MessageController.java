package com.example.rib.Icontrol;

import com.example.rib.Imodel.Messages;
import com.example.rib.Iserv.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/richat-api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send-message")
    public Messages sendMessage(
            @RequestParam String userId,
            @RequestParam String conversationId,
            @RequestParam String content
    ){
        return messageService.sendMessage(userId,conversationId,content);
    }

    @GetMapping("/fetch-message")
    public List<Messages> fetchMessage(
            @RequestParam String conversationId
    ){
        return messageService.fetchMessages(conversationId);
    }

    @PutMapping("/update-message")
    public Messages updateMessage(
            @RequestParam String userId,
            @RequestParam String conversationId,
            @RequestParam String newContent
    ){
        return messageService.updateMessages(userId, conversationId, newContent);
    }
    @DeleteMapping("/delete-message")
    public String deleteMessage(
            @RequestParam String userId,
            @RequestParam String conversationId
    ){
        messageService.deleteMessages(userId, conversationId);
        return "message deleted";
    }


}
