package com.ikotomanov.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Message {
    protected String content;

    public abstract MessageType getType();
}
