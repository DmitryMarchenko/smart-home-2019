package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.alarm.TurnOnAlarmState;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.notify.Notifier;

public class AlarmDecorator implements EventHandler {
    private EventHandler eventHandler;
    private SmartHome smartHome;
    private Notifier notifier;

    public AlarmDecorator(EventHandler eventHandler, SmartHome smartHome, Notifier notifier) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
        this.notifier = notifier;
    }

    @Override
    public void handle(Event event) {
        if (smartHome.getAlarmState() instanceof ActiveAlarmState && event instanceof SensorEvent) {
            smartHome.getAlarm().alarmTurnOn();
            notifier.notify("Alarm!!!");
        } else if (smartHome.getAlarmState() instanceof TurnOnAlarmState) {
            notifier.notify("Alarm!!!");
        } else {
            eventHandler.handle(event);
        }
    }
}
