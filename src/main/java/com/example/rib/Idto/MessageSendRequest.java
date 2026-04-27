package com.example.rib.Idto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MessageSendRequest {

    @NonNull
    private String content;

    @NonNull
    private String id;

    @NonNull
    private String conversationId;

    public MessageSendRequest(@NonNull String content, @NonNull String id, @NonNull String conversationId) {
        this.content = content;
        this.id = id;
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
