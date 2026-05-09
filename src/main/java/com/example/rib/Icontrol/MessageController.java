package com.example.rib.Icontrol;

import com.example.rib.Idto.MessageSendRequest;
import com.example.rib.Imodel.Messages;
import com.example.rib.Iserv.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@Tag(name="messages")
@RestController
@RequestMapping("/richat/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Sending messages", description ="Like creating a message to another user")
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

    @Operation(summary = "fetching messages", description = "fetching your related messages")
    @GetMapping("/message")
    public List<Messages> fetchMessage(
            @RequestParam String conversationId
    ){
        return messageService.fetchMessages(conversationId);
    }

    @Operation(summary = "update message", description = "User update their messages")
    @PutMapping("/message")
    public Messages updateMessage(
            @RequestParam String userId,
            @RequestParam String conversationId,
            @RequestParam String newContent
    ){
        return messageService.updateMessages(userId, conversationId, newContent);
    }
    @Operation(summary = "deleting messages", description = "delete messages by id")
    @DeleteMapping("/message")
    public String deleteMessage(
            @RequestParam String userId,
            @RequestParam String conversationId
    ){
        messageService.deleteMessages(userId, conversationId);
        return "message deleted";
    }


}
