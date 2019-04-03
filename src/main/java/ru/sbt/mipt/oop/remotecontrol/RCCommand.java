package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;

public interface RCCommand {
    void execute(SmartHome smartHome);
}
