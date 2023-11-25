package com.yovvis.cli.pattern;

/**
 * 描述
 *
 * @author yovvis
 * @date 2023/11/25
 */
public class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}
