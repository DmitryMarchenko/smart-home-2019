package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

public class CloseHallDoorCommand implements Command {
    @Override
    public void execute(SmartHome smartHome) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Room)) {
                return;
            }
            Room room = (Room) obj;
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() +  " was closed.");
                }
            }
        });
    }
}
