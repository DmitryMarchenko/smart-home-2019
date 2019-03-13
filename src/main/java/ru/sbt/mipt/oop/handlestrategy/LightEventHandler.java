package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SensorEventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_ON;

public class LightEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isLightEvent(event)) {
            return;
        }
        Action action = new Action(event.getObjectId(), event.getType(), false);
        smartHome.execute(action);
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
