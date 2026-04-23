package com.example.rib.Idto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageSendRequest {

    @NonNull
    private String content;

    @NonNull
    private String id;
}
