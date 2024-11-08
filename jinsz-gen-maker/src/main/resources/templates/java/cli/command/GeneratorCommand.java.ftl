package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.generator.file.MainFileGeneration;
import ${basePackage}.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


@Data
@Command(name = "generator",description = "生成代码",mixinStandardHelpOptions = true)
public class GeneratorCommand implements Callable<Integer> {

<#list modelConfig.models as modelInfo>

    @CommandLine.Option(names = {<#if modelIofo.abbr??>"-${modelInfo.abbr}", </#if>"--${modelInfo.fieldName}"}, arity = "0..1", <#if modelInfo.description??>description = "${modelInfo.description}",</#if>interactive = true ,echo = true)
    private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#list>


    @Override
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        MainFileGeneration.doMainGeneration(dataModel);
        return 0;
    }

}





