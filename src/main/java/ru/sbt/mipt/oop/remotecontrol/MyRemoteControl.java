package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.SmartHome;

import java.util.HashMap;

public class MyRemoteControl implements RemoteControl {
    private String rcId;
    private SmartHome smartHome;
    private HashMap<String, RCCommand> codeToCommand;

    public MyRemoteControl(String rcId, SmartHome smartHome) {
        this.rcId = rcId;
        this.smartHome = smartHome;
    }

    public void RegistryCommand(String buttonCode, RCCommand command) {
        if (buttonCode.equals("A") || buttonCode.equals("B") ||
                buttonCode.equals("C") || buttonCode.equals("D") ||
                buttonCode.equals("1") || buttonCode.equals("2") ||
                buttonCode.equals("3") || buttonCode.equals("4")) {
            codeToCommand.put(buttonCode, command);
        }
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        codeToCommand.get(buttonCode).execute(smartHome);
    }
}
