package com.yovvis.maker.template.model;

import com.yovvis.maker.meta.Meta;
import lombok.Data;

/**
 * 模板制作配置
 *
 * @author yovvis
 * @date 2024/1/6
 */
@Data
public class TemplateMakerConfig {
    private Long id;

    private Meta meta = new Meta();

    private String originProjectPath;

    private TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();

    private TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();

    private TemplateMakerOutputConfig outputConfig = new TemplateMakerOutputConfig();
}
