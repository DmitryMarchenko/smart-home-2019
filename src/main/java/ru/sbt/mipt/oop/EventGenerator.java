package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;

public interface EventGenerator {
    Event getNextEvent();
}
