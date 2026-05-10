package com.example.rib.Idto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class MessageSendRequest {

    @NonNull
    @NotBlank(message = "content empty")
    private String content;

    @NonNull
    @NotBlank(message = "id not extracted")
    private String id;

    @NonNull
    @NotBlank(message = "no conversation selected")
    private String conversationId;

    public MessageSendRequest(@NonNull String content, @NonNull String id, @NonNull String conversationId) {
        this.content = content;
        this.id = id;
        this.conversationId = conversationId;
    }
}
