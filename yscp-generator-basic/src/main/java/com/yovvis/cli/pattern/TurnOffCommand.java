package com.yovvis.cli.pattern;

/**
 * 描述
 *
 * @author yovvis
 * @date 2023/11/25
 */
public class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
