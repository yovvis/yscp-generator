package com.yovvis.maker.template.model;

import lombok.Data;

/**
 * 输出规则配置
 *
 * @author yovvis
 * @date 2024/1/7
 */
@Data
public class TemplateMakerOutputConfig {

    // 从未分组的文件中移除组内同名文件
    private boolean removeGroupFilesFromRoot = true;
}
