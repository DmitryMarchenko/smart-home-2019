package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

public class TurnOnHallLightsCommand implements RCCommand {
    @Override
    public void execute(SmartHome smartHome) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Room)) {
                return;
            }
            Room room = (Room) obj;
            if (room.getName().equals("hall")) {
                for (Light light : room.getLights()) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " was turned on.");
                }
            }
        });
    }
}
