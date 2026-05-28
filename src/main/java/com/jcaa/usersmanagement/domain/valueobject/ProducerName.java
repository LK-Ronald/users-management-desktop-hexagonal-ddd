package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProducerNameException;

import java.util.Objects;

public record ProducerName(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public ProducerName {
        final String normalizedValue = Objects.requireNonNull(value, "ProducerName cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidProducerNameException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidProducerNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
