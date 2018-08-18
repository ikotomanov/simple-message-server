package com.ikotomanov.model;

import lombok.Getter;

public enum MessageType {
    TEXT("send_text"),
    EMOTION("send_emotion");

    @Getter
    private final String value;

    MessageType(String v) {
        value = v;
    }

    public static MessageType fromValue(String v) {
        for (MessageType type: MessageType.values()) {
            if (type.value.equals(v)) {
                return type;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
