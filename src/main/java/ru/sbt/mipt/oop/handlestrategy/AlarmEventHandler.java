package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.AlarmEventType;
import ru.sbt.mipt.oop.event.Event;

public class AlarmEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public AlarmEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(Event event) {
        if (!(event instanceof AlarmEvent)) {
            return;
        }
        AlarmEvent alarmEvent = (AlarmEvent) event;
        if (alarmEvent.getType() == AlarmEventType.ALARM_ACTIVATE) {
            smartHome.getAlarm().activate(alarmEvent.getCode());
        } else if (alarmEvent.getType() == AlarmEventType.ALARM_DEACTIVATE){
            smartHome.getAlarm().deactivate(alarmEvent.getCode());
        }
    }
}
