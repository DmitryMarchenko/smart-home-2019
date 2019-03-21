package ru.sbt.mipt.oop.notify;

public class StdOutNotifier implements Notifier {
    @Override
    public void notify(String msg) {
        System.out.println(msg);
    }
}
