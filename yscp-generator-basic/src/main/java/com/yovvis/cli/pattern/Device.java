package com.yovvis.cli.pattern;

/**
 * 设备
 *
 * @author yovvis
 * @date 2023/11/25
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " 打开设备");
    }

    public void turnOff() {
        System.out.println(name + " 关闭设备");
    }
}
