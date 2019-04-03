package ru.sbt.mipt.oop.alarm;

public abstract class AlarmState implements Alarming {
    public Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activate(String code);
    public abstract void deactivate(String code);
    public abstract void alarmTurnOn();
}
