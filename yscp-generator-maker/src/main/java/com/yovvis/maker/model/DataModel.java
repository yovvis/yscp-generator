package com.yovvis.maker.model;

import lombok.Data;

/**
 * 静态模板配置
 *
 * @author yovvis
 */
@Data
public class DataModel {

    /**
     * 是否循环
     */
    private boolean loop;

    /**
     * 作者
     */
    private String author = "Yovvis";

    /**
     * 输出信息
     */
    private String outputText = "输出信息：";
}
