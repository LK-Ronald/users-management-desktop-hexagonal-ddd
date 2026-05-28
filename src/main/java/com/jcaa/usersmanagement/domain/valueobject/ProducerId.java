package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProducerIdException;

import java.util.Objects;

public record ProducerId(String value) {

    public ProducerId {
        final String normalizedValue = Objects.requireNonNull(value, "ProducerId cannot be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidProducerIdException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
