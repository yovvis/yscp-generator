package com.yovvis.maker;

import com.yovvis.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        // 生成代码生成器
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}