package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commandsendstrategy.PrintCommandSender;
import ru.sbt.mipt.oop.eventtimelinestrategy.OrderedGenerateAndHandleEventTimeline;
import ru.sbt.mipt.oop.handlestrategy.TurnOffAllLightsAfterHallDoorClosed;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.randomgeneratestrategy.RandomChooseForNextSensorEvent;
import ru.sbt.mipt.oop.randomstopstrategy.ProbabilisticStopper;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        EventTimeline eventTimeline = new OrderedGenerateAndHandleEventTimeline(
                new RandomChooseForNextSensorEvent(),
                new TurnOffAllLightsAfterHallDoorClosed(
                        new JsonSmartHomeReader("smart-home-1.js").getSmartHome(),
                        new PrintCommandSender()
                ),
                new ProbabilisticStopper(0.05)
        );

        // начинаем цикл обработки событий
        eventTimeline.process();
    }
}
