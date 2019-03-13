package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.SensorEventType;

public interface Actionable {
    void execute(Action action);
}
