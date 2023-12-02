package com.yovvis.cli.command;

import cn.hutool.core.io.FileUtil;
import com.yovvis.utils.PathUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * 生成文件目录
 *
 * @author yovvis
 * @date 2023/11/25
 */
@Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Override
    public void run() {
        String projectPath = PathUtils.getRunTimePath();
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile, "yscp-generator-demo/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
