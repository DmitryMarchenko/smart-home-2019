package ru.sbt.mipt.oop.generatestrategy;

import ru.sbt.mipt.oop.EventGenerator;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.AlarmEventType;
import ru.sbt.mipt.oop.event.Event;

public class RandomChooseForNextAlarmEvent implements EventGenerator {
    @Override
    public Event getNextEvent() {
        AlarmEventType sensorEventType = AlarmEventType.values()[(int) (AlarmEventType.values().length * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        String code = "ThisIsRightCode";
        if (Math.random() < 0.3) {
            code = "ThisIsWrongCode";
        }
        return new AlarmEvent(sensorEventType, objectId, code);
    }
}
