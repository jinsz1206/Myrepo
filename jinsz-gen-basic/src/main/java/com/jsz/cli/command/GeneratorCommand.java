package com.jsz.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.jsz.generator.MainGeneration;
import com.jsz.model.MainTemplateConfig;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * @Title: GeneratorCommand
 * @Author jsz
 * @Package com.jsz.cli.command
 * @Date 2024/11/6 17:27
 * @description:
 */


@Command(name = "Generator",mixinStandardHelpOptions = true)
public class GeneratorCommand implements Callable<Integer> {


    @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "author name")
    public String author = "jsz";

    @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1",description = "outputText")
    public String outputText = "result";

    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1",description = "是否循环")
    public boolean loop = true;

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置的信息： " + mainTemplateConfig);
        MainGeneration.doMainGeneration(mainTemplateConfig);
        return 0;
    }

}





