package com.yovvis.maker.template;

import com.yovvis.maker.meta.Meta.FileConfig.FileInfo;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.yovvis.maker.meta.Meta;
import com.yovvis.maker.meta.enums.FileGenerateTypeEnum;
import com.yovvis.maker.meta.enums.FileTypeEnum;
import com.yovvis.maker.template.enums.FileFilterRangeEnum;
import com.yovvis.maker.template.enums.FileFilterRuleEnum;
import com.yovvis.maker.template.model.FileFilterConfig;
import com.yovvis.maker.template.model.TemplateMakerFileConfig;
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
            + "yscp-generator-demo-projects/springboot-init";
        List<String> inputFilePathList = new ArrayList<>();
        String fileInputPath1 = "src/main/java/com/yupi/springbootinit/common";
        String fileInputPath2 = "src/main/java/com/yupi/springbootinit/controller";
        inputFilePathList.add(fileInputPath1);
        inputFilePathList.add(fileInputPath2);
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
        String searchStr = "BaseResponse";

        TemplateMakerFileConfig.FileInfoConfig fileConfig1 = new TemplateMakerFileConfig.FileInfoConfig();
        fileConfig1.setPath(fileInputPath1);
        List<FileFilterConfig> fileFilterConfigList = new ArrayList<>();
        FileFilterConfig fileFilterConfig = FileFilterConfig.builder().range(FileFilterRangeEnum.FILE_NAME.getValue())
            .rule(FileFilterRuleEnum.CONTAINS.getValue()).value("Base").build();
        fileFilterConfigList.add(fileFilterConfig);
        fileConfig1.setFilterConfigList(fileFilterConfigList);

        TemplateMakerFileConfig.FileInfoConfig fileConfig2 = new TemplateMakerFileConfig.FileInfoConfig();
        fileConfig2.setPath(fileInputPath2);

        List<TemplateMakerFileConfig.FileInfoConfig> fileInfoConfigList = Arrays.asList(fileConfig1, fileConfig2);
        TemplateMakerFileConfig templateMakerFileConfig = new TemplateMakerFileConfig();

        templateMakerFileConfig.setFiles(fileInfoConfigList);

        // 分组配置
        TemplateMakerFileConfig.FileGroupConfig fileGroupConfig = new TemplateMakerFileConfig.FileGroupConfig();
        fileGroupConfig.setCondition("outputText");
        fileGroupConfig.setGroupKey("test");
        fileGroupConfig.setGroupName("测试分组");
        templateMakerFileConfig.setFileGroupConfig(fileGroupConfig);

        long l = TemplateMaker.makeTemplate(meta, originProjectPath, templateMakerFileConfig, modelInfo, searchStr,
            1743536105746653184L);
        System.out.println(l);
    }

    /**
     * 制作模板
     *
     * @param newMeta
     * @param originProjectPath
     * @param templateMakerFileConfig
     * @param modelInfo
     * @param searchStr
     * @param id
     * @return
     */
    private static long makeTemplate(Meta newMeta, String originProjectPath,
        TemplateMakerFileConfig templateMakerFileConfig, Meta.ModelConfig.ModelInfo modelInfo, String searchStr,
        Long id) {
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
        // 1、项目基本信息 newMeta
        // 2、输入文件信息
        // 挖坑的项目路径
        // File tempFile = new File(templatePath);
        // templatePath = tempFile.getAbsolutePath();
        String sourceRootPath =
            templatePath + File.separator + FileUtil.getLastPathEle(Paths.get(originProjectPath)).toString();
        // win系统需要转义路径
        sourceRootPath = sourceRootPath.replaceAll("\\\\", "/");

        List<TemplateMakerFileConfig.FileInfoConfig> fileInfoConfigList = templateMakerFileConfig.getFiles();
        // 遍历输出文件
        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>();
        for (TemplateMakerFileConfig.FileInfoConfig fileInfoConfig : fileInfoConfigList) {
            String inputFilePath = fileInfoConfig.getPath();
            String inputFileAbsolutePath = sourceRootPath + File.separator + inputFilePath;
            // !一定是绝对路径
            // 得到过滤后的文件列表
            List<File> fileList = FileFilter.doFilter(inputFileAbsolutePath, fileInfoConfig.getFilterConfigList());
            for (File file : fileList) {
                Meta.FileConfig.FileInfo fileInfo = makeFileTemplate(modelInfo, searchStr, sourceRootPath, file);
                newFileInfoList.add(fileInfo);
            }
        }
        // 如果是文件组
        TemplateMakerFileConfig.FileGroupConfig fileGroupConfig = templateMakerFileConfig.getFileGroupConfig();
        if (fileGroupConfig != null) {
            String condition = fileGroupConfig.getCondition();
            String groupKey = fileGroupConfig.getGroupKey();
            String groupName = fileGroupConfig.getGroupName();

            Meta.FileConfig.FileInfo groupFileInfo = new Meta.FileConfig.FileInfo();
            groupFileInfo.setCondition(condition);
            groupFileInfo.setGroupKey(groupKey);
            groupFileInfo.setGroupName(groupName);
            // 文件全放到一个分组内
            groupFileInfo.setFiles(newFileInfoList);
            newFileInfoList = new ArrayList<>();
            newFileInfoList.add(groupFileInfo);
        }
        // 三、生成配置文件
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";
        // 如果已经有meta.json代表已生成过了
        if (FileUtil.exist(metaOutputPath)) {
            Meta oldMeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath), Meta.class);
            // 1、追加配置
            List<Meta.FileConfig.FileInfo> fileInfoList = oldMeta.getFileConfig().getFiles();
            fileInfoList.addAll(newFileInfoList);
            List<Meta.ModelConfig.ModelInfo> modelInfoList = oldMeta.getModelConfig().getModels();
            modelInfoList.add(modelInfo);

            // 配置去重
            oldMeta.getFileConfig().setFiles(distinctFiles(fileInfoList));
            oldMeta.getModelConfig().setModels(distinctModels(modelInfoList));

            // 2、输出元信息文件
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(oldMeta), metaOutputPath);
        } else {
            Meta.FileConfig fileConfig = new Meta.FileConfig();
            newMeta.setFileConfig(fileConfig);
            fileConfig.setSourceRootPath(sourceRootPath);
            List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();
            fileConfig.setFiles(fileInfoList);
            fileInfoList.addAll(newFileInfoList);

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
     * 制作模板文件
     *
     * @param modelInfo
     * @param searchStr
     * @param sourceRootPath
     * @param inputFile
     * @return
     */
    private static Meta.FileConfig.FileInfo makeFileTemplate(Meta.ModelConfig.ModelInfo modelInfo, String searchStr,
        String sourceRootPath, File inputFile) {

        String fileInputAbsolutePath = inputFile.getAbsolutePath();
        fileInputAbsolutePath = fileInputAbsolutePath.replaceAll("\\\\", "/");

        // 要挖坑的文件
        String fileInputPath = fileInputAbsolutePath.replace(sourceRootPath + "/", "");
        String fileOutputPath = fileInputPath + ".ftl";
        // 二、使用字符串替换，生成模板文件
        String fileOutputAbsolutePath = fileInputAbsolutePath + ".ftl";
        String fileContent;
        // 如果已经有同名的ftl文件，代表已经输出过了
        if (FileUtil.exist(fileOutputAbsolutePath)) {
            fileContent = FileUtil.readUtf8String(fileOutputAbsolutePath);
        } else {
            fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        }
        String replacement = String.format("${%s}", modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent, searchStr, replacement);

        // 文件配置信息
        Meta.FileConfig.FileInfo fileInfo = new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());
        // 如果和原文件一致就静态挖坑
        if (fileContent.equals(newFileContent)) {
            fileInfo.setOutputPath(fileInputPath);
            fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
        } else {
            // 输出模板文件
            fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
            FileUtil.writeUtf8String(newFileContent, fileOutputAbsolutePath);
        }
        return fileInfo;
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
