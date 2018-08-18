package com.ikotomanov.model;

public class MessageFactory {

    public static Message createMessage(String type) {
        MessageType messageType = MessageType.fromValue(type);

        if (MessageType.TEXT.equals(messageType))
            return new TextMessage();
        else if (MessageType.EMOTION.equals(messageType))
            return new EmotionMessage();
        else
            return null;
    }
}
