package ru.sbt.mipt.oop.alarm;

public class DeactiveAlarmState extends AlarmState {

    public DeactiveAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new ActiveAlarmState(alarm));
            alarm.notify("Alarm successfully activated.");
        } else {
            alarm.changeState(new TurnOnAlarmState(alarm));
            alarm.notify("Alarm! Wrong activation code!");
        }
    }

    @Override
    public void deactivate(String code) {
        if (!code.equals(alarm.getCode())) {
            alarm.changeState(new TurnOnAlarmState(alarm));
            alarm.notify("Alarm! Wrong deactivation code!");
        }
    }

    @Override
    public void alarmTurnOn() {
        alarm.changeState(new TurnOnAlarmState(alarm));
        alarm.notify("Alarm!!!");
    }
}
