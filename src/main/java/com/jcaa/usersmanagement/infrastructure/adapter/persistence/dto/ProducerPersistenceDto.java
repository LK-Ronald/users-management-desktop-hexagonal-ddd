package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record ProducerPersistenceDto(
        String id,
        String name,
        String EntityType,
        String TypeActivity,
        String country,
        String city,
        String street,
        String postal,
        String createdAt
) {
}
