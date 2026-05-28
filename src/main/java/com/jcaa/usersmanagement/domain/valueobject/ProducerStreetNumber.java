package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProducerStreetNumberException;

import java.util.Objects;

public record ProducerStreetNumber(String value) {

    public ProducerStreetNumber {
        final String normalizedValue = Objects.requireNonNull(value, "ProducerStreetNumber cannot be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidProducerStreetNumberException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
