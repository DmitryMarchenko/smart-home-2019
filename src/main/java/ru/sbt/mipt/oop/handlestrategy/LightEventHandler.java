package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.smarthomeobjects.Light;

public class LightEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(Event event) {
        if (!(event instanceof SensorEvent)) {
            return;
        }
        SensorEvent sensorEvent = (SensorEvent) event;
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            if (sensorEvent.getObjectId().equals(light.getId())) {
                if (sensorEvent.getType() == SensorEventType.LIGHT_ON) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " was turned on.");
                }
                if (sensorEvent.getType() == SensorEventType.LIGHT_OFF) {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " was turned off.");
                }
            }
        });
    }
}
