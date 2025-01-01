package com.tally.outbox.message.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete");

    private final String name;
}
