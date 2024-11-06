package com.jsz.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.jsz.model.MainTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

/**
 * @Title: ConfigCommand
 * @Author jsz
 * @Package com.jsz.cli.command
 * @Date 2024/11/6 17:26
 * @description:
 */

@CommandLine.Command(name = "config", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field : fields) {
            System.out.println("字段名称" + field.getName());
            System.out.println("字段类型" + field.getType());
        }
        System.out.println("========");


    }
}
