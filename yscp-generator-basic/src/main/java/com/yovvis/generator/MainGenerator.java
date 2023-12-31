package com.yovvis.generator;

import com.yovvis.model.MainTemplateConfig;
import com.yovvis.utils.PathUtils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 *
 * @author yovvis
 */
public class MainGenerator {
    /**
     * 生成
     *
     * @param model
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerator(Object model) throws IOException, TemplateException {
        String projectPath = PathUtils.getRunTimePath();
        // 整个项目根路径
        File parentFile = new File(projectPath).getParentFile();
            String inputPath = new File(parentFile, "yscp-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = outputPath + File.separator + "acm-template/src/com/yovvis/acm/MainTemplate.java";
        DynamicGenerator.doGenerator(inputDynamicPath, outputDynamicPath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig model = new MainTemplateConfig();
        model.setLoop(false);
        model.setAuthor("yovvis");
        model.setOutputText("求和结果为：");
        doGenerator(model);
    }
}
