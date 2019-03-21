package ru.sbt.mipt.oop.notify;

public class SMSNotifier implements Notifier {
    @Override
    public void notify(String msg) {
        System.out.println("Sending SMS! " + msg);
    }
}
