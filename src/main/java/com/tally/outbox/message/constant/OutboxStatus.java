package com.tally.outbox.message.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OutboxStatus {
    INIT,
    PUBLISHED,
    PROCESSED,
    FAILED
    ;
}
