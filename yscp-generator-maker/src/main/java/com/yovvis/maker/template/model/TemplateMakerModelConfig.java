package com.yovvis.maker.template.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模块过滤
 *
 * @author yovvis
 * @date 2024/1/6
 */
@Data
public class TemplateMakerModelConfig {
    /**
     * 文件配置
     */
    private List<ModelInfoConfig> models;

    /**
     * 分组配置
     */
    private ModelGroupConfig modelGroupConfig;


    @NoArgsConstructor
    @Data
    public static class ModelInfoConfig {
        private String fieldName;
        private String type;
        private String description;
        private Object defaultValue;
        private String abbr;
        // 替换哪些文本
        private String replaceText;
    }

    @NoArgsConstructor
    @Data
    public static class ModelGroupConfig {
        private String condition;

        private String groupKey;

        private String groupName;
    }
}
