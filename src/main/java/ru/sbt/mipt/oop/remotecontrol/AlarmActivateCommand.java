package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmActivateCommand implements Command {
    @Override
    public void execute(SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        alarm.changeState(new ActiveAlarmState(alarm));
        alarm.notify("Alarm successfully activated.");
    }
}
