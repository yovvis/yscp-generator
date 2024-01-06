package com.yovvis.maker.template.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件过滤
 *
 * @author yovvis
 * @date 2024/1/6
 */
@Data
public class TemplateMakerFileConfig {
    /**
     * 文件配置
     */
    private List<FileInfoConfig> files;

    /**
     * 分组配置
     */
    private FileGroupConfig fileGroupConfig;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {
        private String path;

        private String condition;

        private List<FileFilterConfig> filterConfigList;
    }

    @NoArgsConstructor
    @Data
    public static class FileGroupConfig {
        private String condition;

        private String groupKey;

        private String groupName;
    }
}
