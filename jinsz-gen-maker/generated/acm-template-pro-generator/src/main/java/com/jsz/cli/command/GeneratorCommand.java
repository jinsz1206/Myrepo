package com.jsz.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.jsz.generator.MainFileGeneration;
import com.jsz.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


@Data
@Command(name = "generator",description = "生成代码",mixinStandardHelpOptions = true)
public class GeneratorCommand implements Callable<Integer> {


    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否生成循环",interactive = true ,echo = true)
    private boolean loop = false;

    @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "作者注释",interactive = true ,echo = true)
    private String author = "jinsz";

    @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出信息",interactive = true ,echo = true)
    private String outputText = "sum = ";


    @Override
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        MainFileGeneration.doMainGeneration(dataModel);
        return 0;
    }

}





