package org.kainos.ea.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(final Entity entity) {
        super("Could not find " + entity.getName());
    }
}
