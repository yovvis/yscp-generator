package com.yovvis.maker.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文件过滤
 *
 * @author yovvis
 * @date 2024/1/6
 */
@Data
public class TemplateMakerFileConfig {
    private List<FileInfoConfig> files;

    @NoArgsConstructor
    @Data
    public static class FileInfoConfig {
        private String path;

        private List<FileFilterConfig> filterConfigList;
    }
}
