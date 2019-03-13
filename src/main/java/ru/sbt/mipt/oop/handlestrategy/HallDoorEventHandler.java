package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.SensorEventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public HallDoorEventHandler(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    turnOffLights();
                }
            }
        }
    }

    private void turnOffLights() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }
}
