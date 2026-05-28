package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public class ProducerPersistenceException extends RuntimeException {

    private static final String MESSAGE_SAVE = "Failed to save producer with ID: '%s'.";
    private static final String MESSAGE_UPDATE = "Failed to update producer with ID: '%s'.";
    private static final String MESSAGE_FIND = "Failed to find producer with ID: '%s'.";
    private static final String MESSAGE_EMAIL = "Failed to find producer with email: '%s'.";
    private static final String MESSAGE_ALL = "Failed to retrieve all producers.";
    private static final String MESSAGE_DELETE = "Failed to delete user with ID: '%s'.";
    private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

    private ProducerPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ProducerPersistenceException becauseSaveFailed(final String procedureId, final Throwable cause) {
        return new ProducerPersistenceException(String.format(MESSAGE_SAVE, procedureId), cause);
    }

    public static ProducerPersistenceException becauseUpdateFailed(final String procedureId, final Throwable cause) {
        return new ProducerPersistenceException(String.format(MESSAGE_UPDATE, procedureId), cause);
    }

    public static ProducerPersistenceException becauseFindByIdFailed(final String procedureId, final Throwable cause) {
        return new ProducerPersistenceException(String.format(MESSAGE_FIND, procedureId), cause);
    }

    public static ProducerPersistenceException becauseFindByEmailFailed(final String email, final Throwable cause) {
        return new ProducerPersistenceException(String.format(MESSAGE_EMAIL, email), cause);
    }

    public static ProducerPersistenceException becauseFindAllFailed(final Throwable cause) {
        return new ProducerPersistenceException(MESSAGE_ALL, cause);
    }

    public static ProducerPersistenceException becauseDeleteFailed(final String procedureId, final Throwable cause) {
        return new ProducerPersistenceException(String.format(MESSAGE_DELETE, procedureId), cause);
    }

    public static ProducerPersistenceException becauseConnectionFailed(final Throwable cause) {
        return new ProducerPersistenceException(MESSAGE_CONNECTION, cause);
    }

}
