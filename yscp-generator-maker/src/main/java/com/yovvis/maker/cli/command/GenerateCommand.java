package com.yovvis.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yovvis.maker.generator.file.FileGenerator;
import com.yovvis.maker.model.DataModel;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

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
        DataModel mainTemplateConfig = new DataModel();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        FileGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
