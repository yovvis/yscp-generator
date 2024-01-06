package com.yovvis.maker.template;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.yovvis.maker.meta.Meta;
import com.yovvis.maker.meta.enums.FileGenerateTypeEnum;
import com.yovvis.maker.meta.enums.FileTypeEnum;
import com.yovvis.maker.utils.PathUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 模板制作工具
 *
 * @author yovvis
 * @date 2024/1/6
 */
public class TemplateMaker {

    public static void main(String[] args) {
        // 项目基本信息
        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM 示例模板生成器");

        // 指定原始目录
        String projectPath = PathUtils.getRunTimePath();
        String originProjectPath = FileUtil.getAbsolutePath(new File(projectPath).getParentFile()) + File.separator
            + "yscp-generator-demo-projects/acm-template";
        String fileInputPath = "src/com/yovvis/acm/MainTemplate.java";

        // 模型参数信息
        // Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
        // modelInfo.setFieldName("outputText");
        // modelInfo.setType("String");
        // modelInfo.setDefaultValue("sum = ");
        // String searchStr = "Sum: ";
        // 模型参数信息
        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
        modelInfo.setFieldName("className");
        modelInfo.setType("String");
        String searchStr = "MainTemplate";
        TemplateMaker.makeTemplate(meta, originProjectPath, fileInputPath, modelInfo, searchStr, 1L);
    }

    /**
     * 制作模板
     *
     * @param newMeta
     * @param originProjectPath
     * @param inputFilePath
     * @param modelInfo
     * @param searchStr
     * @param id
     * @return
     */
    private static long makeTemplate(Meta newMeta, String originProjectPath, String inputFilePath,
        Meta.ModelConfig.ModelInfo modelInfo, String searchStr, Long id) {
        // 没有id则生成id
        if (id == null) {
            id = IdUtil.getSnowflakeNextId();
        }
        // 复制目录
        String projectPath = PathUtils.getRunTimePath();
        String tempDirPath = projectPath + File.separator + ".temp";
        String templatePath = tempDirPath + File.separator + id;
        if (!FileUtil.exist(templatePath)) {
            FileUtil.mkdir(templatePath);
            FileUtil.copy(originProjectPath, templatePath, true);
        }
        // 一、输入信息
        // 1、项目基本信息
        String name = "acm-template-generator";
        String description = "ACM 示例模板生成器";

        // 2、输入文件信息
        // 挖坑的项目路径
        String sourceRootPath =
            templatePath + File.separator + FileUtil.getLastPathEle(Paths.get(originProjectPath)).toString();
        // win系统需要转义路径
        sourceRootPath = sourceRootPath.replaceAll("\\\\", "/");
        // 要挖坑的文件
        String fileOutputPath = inputFilePath + ".ftl";

        // 3、输入模型参数信息

        // 二、使用字符串替换，生成模板文件
        String fileInputAbsolutePath = sourceRootPath + File.separator + inputFilePath;
        String fileOutputAbsolutePath = sourceRootPath + File.separator + fileOutputPath;
        String fileContent;
        // 如果已经有同名的ftl文件，代表已经输出过了
        if (FileUtil.exist(fileOutputAbsolutePath)) {
            fileContent = FileUtil.readUtf8String(fileOutputAbsolutePath);
        } else {
            fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        }
        String replacement = String.format("${%s}", modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent, searchStr, replacement);

        // 输出模板路径
        FileUtil.writeUtf8String(newFileContent, fileOutputAbsolutePath);

        // 文件配置信息
        Meta.FileConfig.FileInfo fileInfo = new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(inputFilePath);
        fileInfo.setOutputPath(fileOutputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());
        fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());

        // 三、生成配置文件
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";
        // 如果已经有meta.json代表已生成过了
        if (FileUtil.exist(metaOutputPath)) {
            Meta oldMeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath), Meta.class);
            // 1、追加配置
            List<Meta.FileConfig.FileInfo> fileInfoList = oldMeta.getFileConfig().getFiles();
            fileInfoList.add(fileInfo);
            List<Meta.ModelConfig.ModelInfo> modelInfoList = oldMeta.getModelConfig().getModels();
            modelInfoList.add(modelInfo);

            // 配置去重
            oldMeta.getFileConfig().setFiles(distinctFiles(fileInfoList));
            oldMeta.getModelConfig().setModels(distinctModels(modelInfoList));

            // 2、输出元信息文件
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(oldMeta), metaOutputPath);
        } else {
            // 1、构造配置参数对象

            Meta.FileConfig fileConfig = new Meta.FileConfig();
            newMeta.setFileConfig(fileConfig);
            fileConfig.setSourceRootPath(sourceRootPath);
            List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();
            fileConfig.setFiles(fileInfoList);
            fileInfoList.add(fileInfo);

            Meta.ModelConfig modelConfig = new Meta.ModelConfig();
            newMeta.setModelConfig(modelConfig);
            List<Meta.ModelConfig.ModelInfo> modelInfoList = new ArrayList<>();
            modelConfig.setModels(modelInfoList);
            modelInfoList.add(modelInfo);
            // 2、输出元信息文件
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(newMeta), metaOutputPath);
        }
        return id;
    }

    /**
     * 文件信息去重
     * 
     * @param fileInfoList
     * @return
     */
    private static List<Meta.FileConfig.FileInfo> distinctFiles(List<Meta.FileConfig.FileInfo> fileInfoList) {
        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>(fileInfoList.stream()
            .collect(Collectors.toMap(Meta.FileConfig.FileInfo::getInputPath, o -> o, (e, r) -> r)).values());
        return newFileInfoList;
    }

    /**
     * 模型去重
     *
     * @param modelInfoList
     * @return
     */
    private static List<Meta.ModelConfig.ModelInfo> distinctModels(List<Meta.ModelConfig.ModelInfo> modelInfoList) {
        List<Meta.ModelConfig.ModelInfo> newModelInfoList = new ArrayList<>(modelInfoList.stream()
            .collect(Collectors.toMap(Meta.ModelConfig.ModelInfo::getFieldName, o -> o, (e, r) -> r)).values());
        return newModelInfoList;
    }
}
