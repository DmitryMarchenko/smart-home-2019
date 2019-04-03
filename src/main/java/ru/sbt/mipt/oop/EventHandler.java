package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.Event;

public interface EventHandler {
    void handle(Event event);
}
