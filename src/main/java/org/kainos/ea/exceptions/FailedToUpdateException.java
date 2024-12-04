package org.kainos.ea.exceptions;

public class FailedToUpdateException extends Exception {
    public FailedToUpdateException(final Entity entity) {
        super("Failed to update " + entity.getName());
    }
}
