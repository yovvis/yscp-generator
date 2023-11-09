package com.yovvis.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        // 项目根路径 D:\idea\yscp-generator
        String projectPath = System.getProperty("user.dir");
        File projectFile = new File(projectPath);
        // 输入路径 ACM模板路径 yscp-generator-demo\acm-template
        String inputPath = new File(projectFile, "yscp-generator-demo" + File.separator + "acm-template").getAbsolutePath();
        // 输出路径
        String outputPath = projectPath;
        // 执行拷贝路径
        copyFilesByRecursive(inputPath, outputPath);
//        copyFilesByHutool(inputPath, outputPath);

        System.out.println(projectPath);
    }

    /**
     * 拷贝文件(Hutool 实现，会将输入路径下所有文件拷贝到输出路径中)
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝文件（目录完整拷贝）
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件拷贝失败！");
        }
    }

    /**
     * 文件A=>目录B,则文件A放在目录B下
     * 文件A=>文件B,则文件A覆盖文件
     * 目录A=>目录B,则目录A放在目录B下
     *
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     *
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    public static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是不是目录
        if (inputFile.isDirectory()) {
            System.out.println("目录名称：" + inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，就直接创建
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取源目录下一层所有文件
            File[] files = inputFile.listFiles();
            // 没有文件直接结束了
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归执行
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            // 是文件，直接复制
            Path destpath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destpath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
