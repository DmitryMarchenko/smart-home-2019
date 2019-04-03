package ru.sbt.mipt.oop.commandsendstrategy;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.command.SensorCommand;

public class PrintCommandSender implements CommandSender {

    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
