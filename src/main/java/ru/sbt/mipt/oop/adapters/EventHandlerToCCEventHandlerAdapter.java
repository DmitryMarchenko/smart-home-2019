package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

public class EventHandlerToCCEventHandlerAdapter implements EventHandler {

    private final ru.sbt.mipt.oop.EventHandler eventHandler;

    public EventHandlerToCCEventHandlerAdapter(ru.sbt.mipt.oop.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handle(new CCSensorEventToSensorEventAdapter(event).getSensorEvent());
    }
}
