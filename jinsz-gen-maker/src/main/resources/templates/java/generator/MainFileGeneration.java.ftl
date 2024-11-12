package ${basePackage}.generator;
import freemarker.template.TemplateException;
import com.jsz.model.DataModel;
import java.io.File;
import java.io.IOException;

/**
 * @Title: MainGeneration
 * @Author jsz
 * @Package ${basePackage}.generator
 * @Date 2024/11/6 9:41
 * @description: 主生成方法
 */
public class MainFileGeneration {


    /**
     *
     * @param model 模型
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
    <#if fileInfo.condition??>
        if(${fileInfo.condition}){
            inputPath = new File(inputRootPath,"${fileInfo.inputPath}").getAbsolutePath();
            outputPath = new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
            <#if fileInfo.generateType == "dynamic">
            DynamicFileGeneration.DoGenerate(inputPath, outputPath, model);
            <#else>
            StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);
            </#if>
        }


    <#else>
        inputPath = new File(inputRootPath,"${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
        <#if fileInfo.generateType == "dynamic">
        DynamicFileGeneration.DoGenerate(inputPath, outputPath, model);
        <#else>
        StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);
        </#if>

    </#if>
</#list>



    }
    
}

