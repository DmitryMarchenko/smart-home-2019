package ru.sbt.mipt.oop.alarm;

public class TurnOnAlarmState extends AlarmState {

    public TurnOnAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new ActiveAlarmState(alarm));
            alarm.notify("Alarm successfully activated.");
        } else {
            alarm.notify("Alarm! Wrong activation code!");
        }
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactiveAlarmState(alarm));
            alarm.notify("Alarm successfully deactivated.");
        } else {
            alarm.notify("Alarm! Wrong deactivation code!");
        }
    }

    @Override
    public void alarmTurnOn() {
        alarm.notify("Alarm is already on!");
    }
}
