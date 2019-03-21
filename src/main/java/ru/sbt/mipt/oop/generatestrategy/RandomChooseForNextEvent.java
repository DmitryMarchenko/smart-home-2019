package ru.sbt.mipt.oop.generatestrategy;

import ru.sbt.mipt.oop.EventGenerator;
import ru.sbt.mipt.oop.event.Event;

import java.util.List;

public class RandomChooseForNextEvent implements EventGenerator {

    private List<EventGenerator> generators;

    public RandomChooseForNextEvent(List<EventGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public Event getNextEvent() {
        EventGenerator nextGenerator = generators.get((int) (Math.random() * generators.size()));
        return nextGenerator.getNextEvent();
    }
}
