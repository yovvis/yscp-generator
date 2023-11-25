package com.yovvis.cli.pattern;

/**
 * 客户端
 *
 * @author yovvis
 * @date 2023/11/25
 */
public class Client {
    public static void main(String[] args) {
        // 设备
        Device tv = new Device("tv");
        Device stereo = new Device("stereo");

        // 命令
        TurnOnCommand tvCommand = new TurnOnCommand(tv);
        TurnOnCommand stereoCommand = new TurnOnCommand(stereo);

        // 遥控器
        RemoteControl control = new RemoteControl();

        control.setCommand(tvCommand);
        control.pressPattern();

        control.setCommand(stereoCommand);
        control.pressPattern();
    }
}
