package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 */
public class StaticGenerator {

    /**
    * 拷贝文件(Hutool 实现，会将输入路径下所有文件拷贝到输出路径中)
    *
    * @param inputPath 输入路径
    * @param outputPath 输出路径
    */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}