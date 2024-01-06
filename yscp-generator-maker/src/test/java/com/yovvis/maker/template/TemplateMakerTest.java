package com.yovvis.maker.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.yovvis.maker.meta.Meta;
import com.yovvis.maker.template.enums.FileFilterRangeEnum;
import com.yovvis.maker.template.enums.FileFilterRuleEnum;
import com.yovvis.maker.template.model.FileFilterConfig;
import com.yovvis.maker.template.model.TemplateMakerConfig;
import com.yovvis.maker.template.model.TemplateMakerFileConfig;
import com.yovvis.maker.template.model.TemplateMakerModelConfig;
import com.yovvis.maker.utils.PathUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TemplateMakerTest {

    @Test
    public void makeTemplate() {

    }

    @Test
    public void testMakeTemplateBug1() {
        // 项目基本信息
        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM 示例模板生成器");

        // 指定原始目录
        String projectPath = PathUtils.getRunTimePath();
        String originProjectPath = FileUtil.getAbsolutePath(new File(projectPath).getParentFile()) + File.separator
            + "yscp-generator-demo-projects/springboot-init";
        String fileInputPath1 = "src/main/java/com/yupi/springbootinit/common";
        // 模型分组配置
        TemplateMakerModelConfig templateMakerModelConfig = new TemplateMakerModelConfig();
        // -根型配置
        TemplateMakerModelConfig.ModelInfoConfig modelInfoConfigl = new TemplateMakerModelConfig.ModelInfoConfig();
        modelInfoConfigl.setFieldName("url");
        modelInfoConfigl.setType("String");
        modelInfoConfigl.setDefaultValue("jdbc:mysql://localhost:3306/my_db");
        modelInfoConfigl.setReplaceText("jdbc:mysql://localhost:3306/my_db");

        List<TemplateMakerModelConfig.ModelInfoConfig> modelInfoConfigList = Arrays.asList(modelInfoConfigl);
        templateMakerModelConfig.setModels(modelInfoConfigList);
        // 文件过滤
        TemplateMakerFileConfig templateMakerFileConfig = new TemplateMakerFileConfig();
        TemplateMakerFileConfig.FileInfoConfig fileConfig = new TemplateMakerFileConfig.FileInfoConfig();
        fileConfig.setPath(fileInputPath1);
        templateMakerFileConfig.setFiles(Arrays.asList(fileConfig));

        long l = TemplateMaker.makeTemplate(meta, originProjectPath, templateMakerFileConfig, templateMakerModelConfig,
            null, 1L);
        System.out.println(l);
    }

    @Test
    public void testMakeTemplateBug2() {
        // 项目基本信息
        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM 示例模板生成器");

        // 指定原始目录
        String projectPath = PathUtils.getRunTimePath();
        String originProjectPath = FileUtil.getAbsolutePath(new File(projectPath).getParentFile()) + File.separator
            + "yscp-generator-demo-projects/springboot-init";
        String fileInputPath1 = "src/main/java/com/yupi/springbootinit/common";
        // 模型分组配置
        TemplateMakerModelConfig templateMakerModelConfig = new TemplateMakerModelConfig();
        // -根型配置
        TemplateMakerModelConfig.ModelInfoConfig modelInfoConfigl = new TemplateMakerModelConfig.ModelInfoConfig();
        modelInfoConfigl.setFieldName("className");
        modelInfoConfigl.setType("String");
        modelInfoConfigl.setReplaceText("BaseResponse");

        List<TemplateMakerModelConfig.ModelInfoConfig> modelInfoConfigList = Arrays.asList(modelInfoConfigl);
        templateMakerModelConfig.setModels(modelInfoConfigList);
        // 文件过滤
        TemplateMakerFileConfig templateMakerFileConfig = new TemplateMakerFileConfig();
        TemplateMakerFileConfig.FileInfoConfig fileConfig = new TemplateMakerFileConfig.FileInfoConfig();
        fileConfig.setPath(fileInputPath1);
        templateMakerFileConfig.setFiles(Arrays.asList(fileConfig));

        long l = TemplateMaker.makeTemplate(meta, originProjectPath, templateMakerFileConfig, templateMakerModelConfig,
            null, 1L);
        System.out.println(l);
    }

    @Test
    public void testMakeTemplateWithJson() {
        String configStr = ResourceUtil.readUtf8Str("templateMaker.json");
        TemplateMakerConfig templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        long l = TemplateMaker.makeTemplate(templateMakerConfig);
        System.out.println(l);
    }

    /**
     * 制作SpringBoot 模板
     */
    @Test
    public void testMakeSpringBootTemplate() {
        String rootPath = "examples/springboot-init/";
        String configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker.json");
        TemplateMakerConfig templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        long l = TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker1.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker2.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker3.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker4.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker5.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker6.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker7.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        configStr = ResourceUtil.readUtf8Str(rootPath + "templateMaker8.json");
        templateMakerConfig = JSONUtil.toBean(configStr, TemplateMakerConfig.class);
        TemplateMaker.makeTemplate(templateMakerConfig);

        System.out.println(l);
    }
}