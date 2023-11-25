package com.yovvis.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yovvis.generator.MainGenerator;
import com.yovvis.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * 生成代码
 *
 * @author yovvis
 * @date 2023/11/25
 */
@Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable {
    /**
     * 是否循环
     */
    @Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", echo = true, interactive = true)
    private boolean loop;

    /**
     * 作者
     */
    @Option(names = {"-a", "--author"}, description = "作者", arity = "0..1", echo = true, interactive = true)
    private String author = "yovvis";

    /**
     * 输出信息
     */
    @Option(names = {"-o", "--outputText"}, description = "输出信息", arity = "0..1", echo = true, interactive = true)
    private String outputText = "输出信息：";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
