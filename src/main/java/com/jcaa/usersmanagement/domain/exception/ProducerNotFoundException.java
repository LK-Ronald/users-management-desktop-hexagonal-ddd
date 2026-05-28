package com.jcaa.usersmanagement.domain.exception;

public class ProducerNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "The producer with id '%s' was not found.";

    public ProducerNotFoundException(String message) {
        super(message);
    }

    public static ProducerNotFoundException becauseIdWasNotFound(String producerId) {
        return new ProducerNotFoundException(String.format(MESSAGE_BY_ID, producerId));
    }
}
