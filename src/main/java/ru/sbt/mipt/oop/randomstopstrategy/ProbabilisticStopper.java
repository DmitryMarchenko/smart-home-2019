package ru.sbt.mipt.oop.randomstopstrategy;

import ru.sbt.mipt.oop.TimelineStopper;

public class ProbabilisticStopper implements TimelineStopper {
    private final Double p;

    public ProbabilisticStopper(Double p) {
        this.p = p;
    }

    @Override
    public boolean stop() {
        return Math.random() < p;
    }
}
