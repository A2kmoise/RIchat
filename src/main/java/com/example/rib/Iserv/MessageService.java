package com.example.rib.Iserv;

import com.example.rib.Imodel.Conversation;
import com.example.rib.Imodel.Messages;
import com.example.rib.Imodel.User;
import com.example.rib.Irepo.ConversationRepository;
import com.example.rib.Irepo.MessagesRepository;
import com.example.rib.Irepo.UsersRepository;
import jakarta.mail.MessageAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;
    private final ConversationRepository conversationRepository;

    public MessageService(MessagesRepository messagesRepository, UsersRepository usersRepository, ConversationRepository conversationRepository){
        this.messagesRepository = messagesRepository;
        this.usersRepository = usersRepository;
        this.conversationRepository = conversationRepository;
    }
    //====================================================
    //creating message
    //====================================================
    public Messages sendMessage(String userId, String conversationId, String content){
        User sender = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    return conversationRepository.save(newConversation);
                });

        Messages message = new Messages();
        message.setContent(content);
        message.setConversation(conversation);
        message.setSender(sender);

        return messagesRepository.save(message);
    }

    //====================================================
    //reading messages
    //====================================================
    public List<Messages> fetchMessages(String conversationId){
         Conversation conversation = conversationRepository.findById(conversationId)
        .orElseThrow(() -> new RuntimeException ("user not found"));

          return messagesRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);
    }

    //====================================================
    //updating messages
    //====================================================
    public Messages updateMessages(String messageId, String userId, String newContent){
        Messages messages  = messagesRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("message not found"));

        if(!messages.getSender().getId().equals(userId)){
            throw new RuntimeException("Access denied");
        }
        messages.setContent(newContent);
        return messagesRepository.save(messages);
    }


    //====================================================
    //deleting messages
    //====================================================
    public String deleteMessages(){
        return "message deleted";
    }

}
