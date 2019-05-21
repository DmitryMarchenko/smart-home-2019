package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;

public interface Command {
    void execute(SmartHome smartHome);
}
