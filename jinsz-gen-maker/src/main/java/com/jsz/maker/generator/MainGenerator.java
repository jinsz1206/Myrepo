package com.jsz.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.jsz.maker.generator.file.DynamicFileGeneration;
import com.jsz.maker.meta.Meta;
import com.jsz.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static java.io.File.*;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);


        //输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + separator + "generated" + separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }


        //输入路径 resources
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResources = classPathResource.getAbsolutePath();
        System.out.println(inputResources);

        //java路径
        String outputBasePackage = meta.getBasePackage();
        //com.jsz  ->  com/jsz
        String outputPackage = StrUtil.join("/",StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputPackage;



        String inputFilePath;
        String outputFilePath;

        //model
        inputFilePath = inputResources + separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + separator +"model/DataModel.java";
        DynamicFileGeneration.DoGenerate(inputFilePath,outputFilePath,meta);


        // cli.command.ConfigCommand
        inputFilePath = inputResources + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResources + File.separator + "templates/java/cli/command/GeneratorCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GeneratorCommand.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResources + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResources + File.separator + "templates/java/cli/CommandExcutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExcutor.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);

        // Main
        inputFilePath = inputResources + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);



        inputFilePath = inputResources + File.separator + "templates/java/generator/DynamicFileGeneration.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicFileGeneration.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);


        inputFilePath = inputResources + File.separator + "templates/java/generator/MainFileGeneration.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainFileGeneration.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);


        inputFilePath = inputResources + File.separator + "templates/java/generator/StaticFileGeneration.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticFileGeneration.java";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);

        //pom.xml
        inputFilePath = inputResources + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + separator + "pom.xml";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);

        //构建jar包
        JarGenerator.doGenerate(outputPath);


        // 封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);



    }
}
