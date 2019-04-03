package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.adapters.EventHandlerToCCEventHandlerAdapter;
import ru.sbt.mipt.oop.commandsendstrategy.PrintCommandSender;
import ru.sbt.mipt.oop.decorators.AlarmDecorator;
import ru.sbt.mipt.oop.handlestrategy.DoorEventHandler;
import ru.sbt.mipt.oop.handlestrategy.HallDoorEventHandler;
import ru.sbt.mipt.oop.handlestrategy.LightEventHandler;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.notify.Notifier;
import ru.sbt.mipt.oop.notify.SMSNotifier;

public class LibraryAdaptedApplication {
    public static void main(String[] args) {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        setUpHandlers(smartHome, sensorEventsManager);
        sensorEventsManager.start();
    }

    private static void setUpHandlers(SmartHome smartHome, SensorEventsManager sensorEventsManager) {
        CommandSender commandSender = new PrintCommandSender();
        Notifier notifier = new SMSNotifier();
        sensorEventsManager.registerEventHandler(new EventHandlerToCCEventHandlerAdapter(
                new AlarmDecorator(new LightEventHandler(smartHome), smartHome, notifier))
        );
        sensorEventsManager.registerEventHandler(new EventHandlerToCCEventHandlerAdapter(
                new AlarmDecorator(new DoorEventHandler(smartHome), smartHome, notifier)
        ));
        sensorEventsManager.registerEventHandler(new EventHandlerToCCEventHandlerAdapter(
                new AlarmDecorator(new HallDoorEventHandler(smartHome, commandSender), smartHome, notifier)
        ));
    }
}
