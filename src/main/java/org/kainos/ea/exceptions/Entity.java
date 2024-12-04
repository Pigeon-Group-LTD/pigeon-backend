package org.kainos.ea.exceptions;

public enum Entity {
    ROOM("Room");

    private final String name;

    Entity(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
