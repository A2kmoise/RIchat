package com.example.rib.Icontrol;

import com.example.rib.Idto.MessageSendRequest;
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

    @PostMapping("/message")
    public Messages sendMessage(
            @RequestBody MessageSendRequest sendRequest
            ){
        return messageService.sendMessage(
                sendRequest.getId(),
                sendRequest.getConversationId(),
                sendRequest.getContent()
        );
    }

    @GetMapping("/message")
    public List<Messages> fetchMessage(
            @RequestParam String conversationId
    ){
        return messageService.fetchMessages(conversationId);
    }

    @PutMapping("/message")
    public Messages updateMessage(
            @RequestParam String userId,
            @RequestParam String conversationId,
            @RequestParam String newContent
    ){
        return messageService.updateMessages(userId, conversationId, newContent);
    }
    @DeleteMapping("/message")
    public String deleteMessage(
            @RequestParam String userId,
            @RequestParam String conversationId
    ){
        messageService.deleteMessages(userId, conversationId);
        return "message deleted";
    }


}
