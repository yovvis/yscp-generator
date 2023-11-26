package com.yovvis.factory;

import com.yovvis.cli.command.ConfigCommand;
import com.yovvis.cli.command.GenerateCommand;
import com.yovvis.cli.command.ListCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
