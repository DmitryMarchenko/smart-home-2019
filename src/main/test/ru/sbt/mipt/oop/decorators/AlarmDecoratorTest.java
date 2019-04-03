package ru.sbt.mipt.oop.decorators;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.alarm.TurnOnAlarmState;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.AlarmEventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.handlestrategy.AlarmEventHandler;
import ru.sbt.mipt.oop.handlestrategy.LightEventHandler;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.notify.SMSNotifier;

import static org.junit.jupiter.api.Assertions.*;

class AlarmDecoratorTest {

    @Test
    void sensorEventDuringActiveAlarm() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        AlarmEventHandler alarmEventHandler = new AlarmEventHandler(smartHome);
        AlarmDecorator lightEventHandler = new AlarmDecorator(new LightEventHandler(smartHome),
                                                                 smartHome, new SMSNotifier());
        AlarmEvent alarmEvent = new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "", "ThisIsRightCode");
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        alarmEventHandler.handle(alarmEvent);
        lightEventHandler.handle(sensorEvent);
        assertTrue(smartHome.getAlarmState() instanceof TurnOnAlarmState);
    }
}