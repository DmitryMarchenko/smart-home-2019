package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.notify.Notifier;

public class Alarm {
    private String code;
    private AlarmState state = new DeactiveAlarmState(this);
    private Notifier notifier;

    public Alarm(String code, Notifier notifier) {
        this.code = code;
        this.notifier = notifier;
    }

    public void changeState(AlarmState state) {
        this.state = state;
    }

    public void activate(String code) {
        state.activate(code);
    }
    public void deactivate(String code) {
        state.deactivate(code);
    }
    public void alarmTurnOn() {
        state.alarmTurnOn();
    }

    public String getCode() {
        return code;
    }

    public AlarmState getState() {
        return state;
    }

    public void notify(String msg) {
        notifier.notify(msg);
    }
}
