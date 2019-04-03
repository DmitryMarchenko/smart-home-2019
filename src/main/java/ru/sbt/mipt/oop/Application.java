package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commandsendstrategy.PrintCommandSender;
import ru.sbt.mipt.oop.decorators.AlarmDecorator;
import ru.sbt.mipt.oop.eventtimelinestrategy.OrderedGenerateAndHandleEventTimeline;
import ru.sbt.mipt.oop.generatestrategy.RandomChooseForNextAlarmEvent;
import ru.sbt.mipt.oop.generatestrategy.RandomChooseForNextEvent;
import ru.sbt.mipt.oop.generatestrategy.RandomChooseForNextSensorEvent;
import ru.sbt.mipt.oop.handlestrategy.AlarmEventHandler;
import ru.sbt.mipt.oop.handlestrategy.DoorEventHandler;
import ru.sbt.mipt.oop.handlestrategy.HallDoorEventHandler;
import ru.sbt.mipt.oop.handlestrategy.LightEventHandler;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.notify.Notifier;
import ru.sbt.mipt.oop.notify.SMSNotifier;
import ru.sbt.mipt.oop.stopstrategy.ProbabilisticStopper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        CommandSender commandSender = new PrintCommandSender();
        Collection<EventHandler> eventHandlers = configureEventHandlers(smartHome, commandSender);
        EventTimeline eventTimeline = new OrderedGenerateAndHandleEventTimeline(
                new RandomChooseForNextEvent(Arrays.asList(new RandomChooseForNextSensorEvent(),
                        new RandomChooseForNextAlarmEvent())),
                eventHandlers,
                new ProbabilisticStopper(0.05)
        );

        // начинаем цикл обработки событий
        eventTimeline.process();
    }

    private static Collection<EventHandler> configureEventHandlers(SmartHome smartHome,
                                                                   CommandSender commandSender) {
        Collection<EventHandler> eventHandlers = new ArrayList<>();
        Notifier notifier = new SMSNotifier();
        eventHandlers.add(new AlarmDecorator(new LightEventHandler(smartHome), smartHome, notifier));
        eventHandlers.add(new AlarmDecorator(new DoorEventHandler(smartHome), smartHome, notifier));
        eventHandlers.add(new AlarmDecorator(new HallDoorEventHandler(smartHome, commandSender), smartHome, notifier));
        eventHandlers.add(new AlarmEventHandler(smartHome));
        return eventHandlers;
    }
}
