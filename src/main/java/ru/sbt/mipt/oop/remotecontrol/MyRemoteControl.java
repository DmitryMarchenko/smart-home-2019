package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.SmartHome;

import java.util.HashMap;

public class MyRemoteControl implements RemoteControl {
    private String rcId;
    private SmartHome smartHome;
    private HashMap<String, Command> codeToCommand = new HashMap<>();

    public MyRemoteControl(String rcId, SmartHome smartHome) {
        this.rcId = rcId;
        this.smartHome = smartHome;
    }

    public void registryCommand(String buttonCode, Command command) {
        codeToCommand.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        codeToCommand.get(buttonCode).execute(smartHome);
    }
}
