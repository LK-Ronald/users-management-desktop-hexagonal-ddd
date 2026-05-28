package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidProducerEntityTypeException;

public enum ProducerEntityType {

    COMPANY,
    ORGANIZATION;

    public static ProducerEntityType fromString(final String value) {
        for (final ProducerEntityType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw InvalidProducerEntityTypeException.becauseValueIsInvalid(value);
    }
}
