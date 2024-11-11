package com.jsz.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {

    private static volatile Meta meta;

    public static Meta getMetaObject(){
        //双检索单例模式
        if(meta == null){
            synchronized (MetaManager.class){
                if(meta == null){
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    public static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("templates/meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        MetaValidator.dovaliAndFill(newMeta);
        return newMeta;
    }

}
