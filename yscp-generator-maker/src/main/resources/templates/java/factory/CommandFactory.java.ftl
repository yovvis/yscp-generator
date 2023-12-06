package ${basePackage}.factory;

import ${basePackage}.cli.command.ConfigCommand;
import ${basePackage}.cli.command.GenerateCommand;
import ${basePackage}.cli.command.ListCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态命令工厂
 *
 * @author ${author}
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
