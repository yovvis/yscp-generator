package com.yovvis.generator;

import cn.hutool.core.io.FileUtil;
import com.yovvis.model.MainTemplateConfig;
import com.yovvis.utils.PathUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 动态生成
 *
 * @author
 */
public class DynamicGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        // 项目路径 D:\idea\yscp-generator

        String projectPath = PathUtils.getRunTimePath();
        String inputPath =
            projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig model = new MainTemplateConfig();
        model.setLoop(false);
        model.setAuthor("yovvis");
        model.setOutputText("结果为：");
        doGenerator(inputPath, outputPath, model);
    }

    /**
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     * @param model 模板数据
     */
    public static void doGenerator(String inputPath, String outputPath, Object model)
        throws IOException, TemplateException {
        // 1、new 出 Configuration对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 2、指定模板文件所在的路轻(为输入路径上一级)
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        // 3、设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        configuration.setNumberFormat("0.######");
        // 4、创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName, "UTF-8");
        // 文件不存在则创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }
        BufferedWriter out =
            new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8));
        template.process(model, out);
        // 关闭流
        out.close();
    }
}
