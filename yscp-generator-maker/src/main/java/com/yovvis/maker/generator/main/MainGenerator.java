package com.yovvis.maker.generator.main;

import java.io.IOException;

import freemarker.template.TemplateException;

/**
 * 生成代码生成器
 *
 * @author
 * @date 2023/12/6
 */
public class MainGenerator extends GenerateTemplate {
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不生成 产品包dist！");
    }
}
