package com.tally.outbox.message;

import com.tally.outbox.message.constant.MessageType;
import com.tally.outbox.message.constant.OutboxStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "outbox")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OutboxMessage {

    private final static int MAX_FAILURE_COUNT = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outbox_id", nullable = false)
    private Long id;

    @Column(name = "message_type", nullable = false)
    private MessageType type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OutboxStatus status = OutboxStatus.INIT;

    @Column(nullable = false)
    private int failureCount = 0;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime processedAt;

    @Builder
    private OutboxMessage(
            final Long id,
            final MessageType type,
            final OutboxStatus status,
            final int failureCount,
            final LocalDateTime createdAt,
            final LocalDateTime processedAt
    ) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.failureCount = failureCount;
        this.createdAt = createdAt;
        this.processedAt = processedAt;
    }

    public static OutboxMessage created(MessageType type) {
        return OutboxMessage.builder()
                .type(type)
                .build();
    }

    public void markProcessed() {
        this.status = OutboxStatus.PROCESSED;
        this.processedAt = LocalDateTime.now();
    }
}
