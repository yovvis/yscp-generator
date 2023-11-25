package com.yovvis.cli.pattern;

/**
 * 遥控器
 *
 * @author yovvis
 * @date 2023/11/25
 */
public class RemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressPattern() {
        command.execute();
    }
}
