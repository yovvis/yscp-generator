package com.yovvis.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.yovvis.model.MainTemplateConfig;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * 描述
 *
 * @author yovvis
 * @date 2023/11/25
 */
@Command(name = "config", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields) {
            System.out.println("字段类型：" + field.getType() + " 字段类型：" + field.getName());
        }
    }
}
