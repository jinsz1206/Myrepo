package com.jsz.maker.generator;

import com.jsz.maker.meta.Meta;
import com.jsz.maker.meta.MetaManager;

public class MainGenerator {
    public static void main(String[] args) {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

    }
}
