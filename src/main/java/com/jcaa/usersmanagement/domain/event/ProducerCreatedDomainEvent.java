package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.Producer;

import java.util.Map;

import lombok.Getter;

@Getter
public final class ProducerCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "producer.created";

    private final Producer producer;

    public ProducerCreatedDomainEvent(final Producer producer) {
        super(EVENT_NAME);
        this.producer = producer;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", producer.getId().value(),
                "name", producer.getName().value(),
                "entityType", producer.getEntityType().name(),
                "typeActivity", producer.getTypeActivity().name(),
                "country", producer.getCountry().value(),
                "city", producer.getCity().value(),
                "street", producer.getStreet().value(),
                "postal", producer.getPostal().value());
    }
}
