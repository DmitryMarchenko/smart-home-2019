package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.notify.StdOutNotifier;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm = new Alarm("ThisIsRightCode", new StdOutNotifier());

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public AlarmState getAlarmState() {
        return alarm.getState();
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        rooms.forEach(room -> room.execute(action));
    }
}
