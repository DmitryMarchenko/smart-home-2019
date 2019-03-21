package ru.sbt.mipt.oop.alarm;

public interface Alarming {
    void activate(String code);
    void deactivate(String code);
    void alarmTurnOn();
}
