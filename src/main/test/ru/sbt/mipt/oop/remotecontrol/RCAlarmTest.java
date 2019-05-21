package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.alarm.ActiveAlarmState;
import ru.sbt.mipt.oop.alarm.TurnOnAlarmState;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RCAlarmTest {

    @Test
    void activateAlarmTest() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        MyRemoteControl remoteControl = new MyRemoteControl("1", smartHome);
        remoteControl.registryCommand("A", new AlarmActivateCommand());
        remoteControl.onButtonPressed("A", "1");
        assertTrue(smartHome.getAlarmState() instanceof ActiveAlarmState);
    }

    @Test
    void turnOnAlarmTest() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        MyRemoteControl remoteControl = new MyRemoteControl("2", smartHome);
        remoteControl.registryCommand("B", new TurnOnAlarmCommand());
        remoteControl.onButtonPressed("B", "2");
        assertTrue(smartHome.getAlarmState() instanceof TurnOnAlarmState);
    }
}
