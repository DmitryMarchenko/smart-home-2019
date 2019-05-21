package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.TurnOnAlarmState;

public class TurnOnAlarmCommand implements Command {
    @Override
    public void execute(SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        alarm.changeState(new TurnOnAlarmState(alarm));
        alarm.notify("Alarm!!!");
    }
}
