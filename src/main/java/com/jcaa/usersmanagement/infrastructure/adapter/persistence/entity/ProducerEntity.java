package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record ProducerEntity(
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
