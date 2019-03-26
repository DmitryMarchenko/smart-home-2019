package ru.sbt.mipt.oop.handlestrategy;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.alarm.DeactiveAlarmState;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.AlarmEventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AlarmEventHandlerTest {

    @Test
    void alarmStatesTest() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        AlarmEventHandler alarmEventHandler = new AlarmEventHandler(smartHome);
        AlarmEvent alarmEvent = new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "", "ThisIsRightCode");
        alarmEventHandler.handle(alarmEvent);
        assertTrue(smartHome.getAlarmState() instanceof ActiveAlarmState);
        alarmEvent = new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "", "ThisIsRightCode");
        alarmEventHandler.handle(alarmEvent);
        assertTrue(smartHome.getAlarmState() instanceof DeactiveAlarmState);
    }
}