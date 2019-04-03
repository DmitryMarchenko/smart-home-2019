package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public HallDoorEventHandler(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void handle(Event event) {
        if (!(event instanceof SensorEvent)) {
            return;
        }
        SensorEvent sensorEvent = (SensorEvent) event;
        if (sensorEvent.getType() != DOOR_CLOSED) {
            return;
        }

        smartHome.execute(obj -> {
            if (!(obj instanceof Room)) {
                return;
            }
            Room room = (Room) obj;
            if (!room.getName().equals("hall")) {
                return;
            }
            for (Door door : room.getDoors()) {
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    turnOffLights();
                    break;
                }
            }
        });
    }

    private void turnOffLights() {
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        });
    }
}
