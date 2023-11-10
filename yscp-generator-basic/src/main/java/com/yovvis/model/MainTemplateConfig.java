package com.yovvis.model;

import lombok.Data;

/**
 * 静态模板配置
 *
 * @author yovvis
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否循环
     */
    private boolean loop;

    /**
     * 作者
     */
    private String author = "yovvis";

    /**
     * 输出信息
     */
    private String outputText = "输出信息：";
}
