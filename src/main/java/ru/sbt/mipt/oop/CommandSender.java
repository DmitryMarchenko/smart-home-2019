package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.SensorCommand;

public interface CommandSender {
    void sendCommand(SensorCommand command);
}
