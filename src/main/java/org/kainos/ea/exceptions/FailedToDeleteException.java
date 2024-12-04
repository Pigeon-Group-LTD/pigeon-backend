package org.kainos.ea.exceptions;

public class FailedToDeleteException extends Exception {
    public FailedToDeleteException(final Entity entity) {
        super("Failed to delete " + entity.getName());
    }
}
