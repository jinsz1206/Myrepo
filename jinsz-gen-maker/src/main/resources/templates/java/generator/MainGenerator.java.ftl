package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import ${basePackage}.generator.file.DynamicFileGeneration;
import ${basePackage}.meta.Meta;
import ${basePackage}.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static java.io.File.*;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);


        //输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + separator + "generated";
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }


        //输入路径 resources
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResources = classPathResource.getAbsolutePath();
        System.out.println(inputResources);

        //java路径
        String outputBasePackage = meta.getBasePackage();
        String outputPackage = StrUtil.join("/",StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputPackage;
        System.out.println(outputBaseJavaPackagePath);

        String inputFilePath;
        String outputFilePath;

        //model
        inputFilePath = inputResources + separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + separator +"model/DataModel.java";
        DynamicFileGeneration.DoGenerate(inputFilePath,outputFilePath,meta);
    }
}
