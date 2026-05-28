package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidProducerTypeActivityException;

public enum ProducerTypeActivity {

    COMMERCIAL,
    INDUSTRIAL,
    SERVICE;

    public static ProducerTypeActivity fromString(final String value) {
        for (final ProducerTypeActivity activity : values()) {
            if (activity.name().equalsIgnoreCase(value)) {
                return activity;
            }
        }
        throw InvalidProducerTypeActivityException.becauseValueIsInvalid(value);
    }
}
