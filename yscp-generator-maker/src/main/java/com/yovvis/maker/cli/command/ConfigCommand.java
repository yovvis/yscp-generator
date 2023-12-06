package com.yovvis.maker.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.yovvis.maker.model.DataModel;
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
        Field[] fields = ReflectUtil.getFields(DataModel.class);
        for (Field field : fields) {
            System.out.println("字段类型：" + field.getType() + " 字段类型：" + field.getName());
        }
    }
}
