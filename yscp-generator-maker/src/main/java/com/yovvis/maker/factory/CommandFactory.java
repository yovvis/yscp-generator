package com.yovvis.maker.factory;

import com.yovvis.maker.cli.command.ConfigCommand;
import com.yovvis.maker.cli.command.GenerateCommand;
import com.yovvis.maker.cli.command.ListCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态命令工厂
 *
 * @author yovvis
 * @date 2023/11/26
 */
public class CommandFactory {
    public static Map<String, Class<?>> COMMAND_MAP;

    static {
        COMMAND_MAP = new HashMap<>();
        COMMAND_MAP.put("generate", GenerateCommand.class);
        COMMAND_MAP.put("config", ConfigCommand.class);
        COMMAND_MAP.put("list", ListCommand.class);
    }

    public static Class<?> getObject(String key) {
        return COMMAND_MAP.get(key);
    }
}
