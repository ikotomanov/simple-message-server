package com.ikotomanov.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TextMessage extends Message  {

    @Override
    @NotBlank
    @Size(min = 1, max = 160)
    public String getContent() {
        return content;
    }

    @Override
    public MessageType getType() {
        return MessageType.TEXT;
    }
}
