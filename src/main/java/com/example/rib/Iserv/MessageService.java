package com.example.rib.Iserv;

import com.example.rib.Irepo.MessagesRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;

    public MessageService(MessagesRepository messagesRepository){
        this.messagesRepository = messagesRepository;
    }
    //====================================================
    //creating message
    //====================================================
    public String sendMessage(){
        return "message sent";
    }

    //====================================================
    //reading messages
    //====================================================
    public String fetchMessages(){
        return "all messages";
    }

    //====================================================
    //updating messages
    //====================================================
    public String updateMessages(){
        return "message updated";
    }


    //====================================================
    //deleting messages
    //====================================================
    public String deleteMessages(){
        return "message deleted";
    }

}
