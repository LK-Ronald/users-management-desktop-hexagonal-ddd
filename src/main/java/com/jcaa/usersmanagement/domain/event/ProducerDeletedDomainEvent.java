package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.ProducerId;

import java.util.Map;

import lombok.Getter;

@Getter
public final class ProducerDeletedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "producer.deleted";

    private final ProducerId producerId;

    public ProducerDeletedDomainEvent(final ProducerId producerId) {
        super(EVENT_NAME);
        this.producerId = producerId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("id", producerId.value());
    }
}
