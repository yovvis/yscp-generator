package com.yovvis.maker.generator;

import com.yovvis.maker.meta.Meta;
import com.yovvis.maker.meta.MetaManager;

/**
 * 描述
 *
 * @author
 * @date 2023/12/6
 */
public class MainGenerator {
    public static void main(String[] args) {
        Meta metaObject = MetaManager.getMetaObject();
        System.out.println(metaObject);
    }
}
