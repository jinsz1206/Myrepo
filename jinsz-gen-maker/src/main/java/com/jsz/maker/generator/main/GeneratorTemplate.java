package com.jsz.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.jsz.maker.generator.JarGenerator;
import com.jsz.maker.generator.ScriptGenerator;
import com.jsz.maker.generator.file.DynamicFileGeneration;
import com.jsz.maker.meta.Meta;
import com.jsz.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static java.io.File.separator;

/**
 * @Title: GeneratorTemplate
 * @Author jsz
 * @Package com.jsz.maker.generator.main
 */
public abstract class GeneratorTemplate {
    protected void doGenerate() throws TemplateException, IOException, InterruptedException{

        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);


        //输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + separator + "generated" + separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        //复制原始文件
        String sourceCopyPath = copySource(meta, outputPath);

        //生成代码
        generateCode(meta, outputPath);

        //构建jar包
        String jarPath = jarBuild(outputPath,meta);

        

        // 封装脚本
        String shellOutputFilePath = BuildScript(outputPath, jarPath);


        //生成精简版程序
        buildDist(outputPath, shellOutputFilePath, sourceCopyPath, jarPath);


    }

    protected void buildDist(String outputPath, String shellOutputFilePath, String sourceCopyPath,String jarPath) {
        String distOutputPath = outputPath + "-dist";
        //拷贝jar包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath,true);
        //拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutputPath,true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath,true);
        //原模版文件
        FileUtil.copy(sourceCopyPath,distOutputPath,true);
    }

    protected  String BuildScript(String outputPath, String jarPath) throws IOException {
        String shellOutputFilePath = outputPath + File.separator + "generate";;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }


    protected  String jarBuild(String outputPath, Meta meta) throws InterruptedException, IOException {
        //构建jar包
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }

    protected  void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
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
        DynamicFileGeneration.DoGenerate(inputFilePath,outputFilePath, meta);


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

        //README.md
        inputFilePath = inputResources + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + separator + "README.md";
        DynamicFileGeneration.DoGenerate(inputFilePath , outputFilePath, meta);
    }

    protected static String copySource(Meta meta, String outputPath) {
        //复制原始文件
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyPath = outputPath + separator + ".source";
        FileUtil.copy(sourceRootPath,sourceCopyPath,false);
        return sourceCopyPath;
    }

}
