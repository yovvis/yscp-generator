package com.yovvis.maker.utils;

import cn.hutool.core.util.ReflectUtil;
import com.yovvis.maker.factory.CommandFactory;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 命令工具类
 *
 * @author yovvis
 * @date 2023/11/26
 */
public class CommandUtils {
    public CommandUtils() {
    }

    /**
     * 预处理命令（强制交互）
     *
     * @param args
     * @return
     */
    public static String[] preCommand(String[] args) {
        // 没有输入，直接交由pico cli框架，输出help信息
        if (args.length < 1) {
            return args;
        }
        // 获取command所对应的Class
        Class<?> commandClass = CommandFactory.getObject(args[0]);
        if (commandClass == null) {
            return args;
        }
        // 1、顺序2、唯一存储容一遍现有参数的容器
        Set<String> currentArgsSet = new LinkedHashSet<>(Arrays.asList(args));
        // 追加必填命令
        Field[] fields = ReflectUtil.getFields(commandClass);
        for (Field field : fields) {
            if (field.isAnnotationPresent(CommandLine.Option.class)) {
                // 获取参数名，追加到容器中
                String optionName = getOptionName(field);
                if (StringUtils.isNotBlank(optionName)) {
                    currentArgsSet.add(optionName);
                }
            }
        }
        return currentArgsSet.toArray(new String[0]);
    }

    /**
     * 获取Option的name属性
     *
     * @param field
     * @return
     */
    public static String getOptionName(Field field) {
        CommandLine.Option option = field.getAnnotation(CommandLine.Option.class);
        // 如果required = ture表示自动追加这个参数
        if (option != null && option.names().length > 0 && option.required() == true) {
            // 追加简写
            return option.names()[0];
        }
        return null;
    }

}
