package com.yovvis.maker.generator.file;

import com.yovvis.maker.model.DataModel;
import com.yovvis.maker.utils.PathUtils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 *
 * @author yovvis
 */
public class FileGenerator {
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
        String inputPath = new File(parentFile, "yscp-generator-demo/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicPath = outputPath + File.separator + "acm-template/src/com/yovvis/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicPath, outputDynamicPath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        DataModel model = new DataModel();
        model.setLoop(false);
        model.setAuthor("yovvis");
        model.setOutputText("求和结果为：");
        doGenerator(model);
    }
}
