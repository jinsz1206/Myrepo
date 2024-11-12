package ${basePackage}.generator;

import com.jsz.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

<#macro generateFile indent fileInfo>
    ${indent}inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
    ${indent}outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        ${indent}StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);
    <#else>
        ${indent}DynamicFileGeneration.DoGenerate(inputPath, outputPath, model);
    </#if>
</#macro>

/**
* 核心生成器
*/
public class MainFileGeneration {

/**
* 生成
*
* @param model 数据模型
* @throws TemplateException
* @throws IOException
*/
public static void doMainGeneration(DataModel model) throws TemplateException, IOException {
    String inputRootPath = "${fileConfig.inputRootPath}";
    String outputRootPath = "${fileConfig.outputRootPath}";

    String inputPath;
    String outputPath;

<#list modelConfig.models as modelInfo>
    ${modelInfo.type} ${modelInfo.fieldName} = model.${modelInfo.fieldName};
</#list>

<#list fileConfig.files as fileInfo>
    <#if fileInfo.groupKey??>
        // groupKey = ${fileInfo.groupKey}
        <#if fileInfo.condition??>
            if(${fileInfo.condition}) {
            <#list fileInfo.files as fileInfo>
                <@generateFile fileInfo=fileInfo indent="           " />
            </#list>
            }
        <#else>
            <#list fileInfo.files as fileInfo>
                <@generateFile fileInfo=fileInfo indent="       " />
            </#list>
        </#if>
    <#else>
        <#if fileInfo.condition??>
            if(${fileInfo.condition}) {
            <@generateFile fileInfo=fileInfo indent="           " />
            }
        <#else>
            <@generateFile fileInfo=fileInfo indent="       " />
        </#if>
    </#if>
</#list>
}
}

