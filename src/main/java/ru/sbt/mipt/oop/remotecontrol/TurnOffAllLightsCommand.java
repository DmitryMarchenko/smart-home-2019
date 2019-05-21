package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthomeobjects.Light;

public class TurnOffAllLightsCommand implements Command {
    @Override
    public void execute(SmartHome smartHome) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            light.setOn(false);
            System.out.println("Light " + light.getId() + " was turned off.");
        });
    }
}
