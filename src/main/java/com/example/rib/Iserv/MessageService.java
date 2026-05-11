package com.example.rib.Iserv;

import com.example.rib.Ienum.ConversationType;
import com.example.rib.Imodel.Conversation;
import com.example.rib.Imodel.ConversationParticipants;
import com.example.rib.Imodel.Messages;
import com.example.rib.Imodel.User;
import com.example.rib.Irepo.ConversationParticipantsRepository;
import com.example.rib.Irepo.ConversationRepository;
import com.example.rib.Irepo.MessagesRepository;
import com.example.rib.Irepo.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationParticipantsRepository conversationParticipantsRepository;

    public MessageService(MessagesRepository messagesRepository,
                          UsersRepository usersRepository,
                          ConversationRepository conversationRepository,
                          ConversationParticipantsRepository conversationParticipantsRepository) {
        this.messagesRepository = messagesRepository;
        this.usersRepository = usersRepository;
        this.conversationRepository = conversationRepository;
        this.conversationParticipantsRepository = conversationParticipantsRepository;
    }


    public Conversation findOrCreateConversation(String senderId,String receiverId){
    User sender = usersRepository.findById(senderId)
            .orElseThrow(()->new RuntimeException("user not found"));

    User receiver = usersRepository.findById(receiverId)
            .orElseThrow(() -> new RuntimeException("user not found"));

    Optional<Conversation> existingConversation = conversationRepository.findPrivateConversationBetweenUsers(
            senderId, receiverId, ConversationType.PRIVATE
    );

    if (existingConversation.isPresent()){
        return existingConversation.get();
    }

        //======================================================
        // Creating conversation
        //======================================================

        Conversation conversation = new Conversation();

    conversation.setConversationType(ConversationType.PRIVATE);

    conversation.setUser(sender);

        Conversation savedConversation =
                conversationRepository.save(conversation);

        ConversationParticipants senderParticipant =
                new ConversationParticipants();

        senderParticipant.setConversation(savedConversation);
        senderParticipant.setUser(sender);
        // add receiver participant
        //====================================================
        ConversationParticipants receiverParticipant =
                new ConversationParticipants();

        receiverParticipant.setConversation(savedConversation);
        receiverParticipant.setUser(receiver);

        //====================================================
        // save participants
        //====================================================
        conversationParticipantsRepository.save(senderParticipant);

        conversationParticipantsRepository.save(receiverParticipant);

        return savedConversation;
    }
    //====================================================
    //creating message
    //====================================================
    public Messages sendMessage(String userId, String conversationId, String content){
        User sender = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

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
    public void deleteMessages(String messageId, String userId){
        Messages messages = messagesRepository.findById(messageId)
                .orElseThrow(()-> new RuntimeException("message not found"));

        if(!messages.getSender().getId().equals(userId)){
            throw new RuntimeException("Access denied");
        }

        messagesRepository.delete(messages);
    }

}
