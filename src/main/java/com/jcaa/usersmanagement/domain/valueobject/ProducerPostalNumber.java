package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProducerPostalNumberException;

import java.util.Objects;

public record ProducerPostalNumber(String value) {

    public ProducerPostalNumber {
        final String normalizedValue = Objects.requireNonNull(value, "ProducerPostalNumber cannot be null").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidProducerPostalNumberException.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
