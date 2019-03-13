package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commandsendstrategy.PrintCommandSender;
import ru.sbt.mipt.oop.eventtimelinestrategy.OrderedGenerateAndHandleEventTimeline;
import ru.sbt.mipt.oop.handlestrategy.DoorEventHandler;
import ru.sbt.mipt.oop.handlestrategy.HallDoorEventHandler;
import ru.sbt.mipt.oop.handlestrategy.LightEventHandler;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.generatestrategy.RandomChooseForNextSensorEvent;
import ru.sbt.mipt.oop.stopstrategy.ProbabilisticStopper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        CommandSender commandSender = new PrintCommandSender();
        Collection<SensorEventHandler> eventHandlers = configureEventHandlers(smartHome, commandSender);
        EventTimeline eventTimeline = new OrderedGenerateAndHandleEventTimeline(
                new RandomChooseForNextSensorEvent(),
                eventHandlers,
                new ProbabilisticStopper(0.05)
        );

        // начинаем цикл обработки событий
        eventTimeline.process();
    }

    private static Collection<SensorEventHandler> configureEventHandlers(SmartHome smartHome,
                                                                         CommandSender commandSender) {
        Collection<SensorEventHandler> eventHandlers = new ArrayList<>();
        eventHandlers.add(new LightEventHandler(smartHome));
        eventHandlers.add(new DoorEventHandler(smartHome));
        eventHandlers.add(new HallDoorEventHandler(smartHome, commandSender));
        return eventHandlers;
    }
}
