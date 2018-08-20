package com.ikotomanov.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmotionMessage extends Message  {

    @Override
    @NotBlank
    @Pattern(regexp = "[^0-9]{2,10}")
    public String getContent() {
        return content;
    }

    @Override
    public MessageType getType() {
        return MessageType.TEXT;
    }
}
