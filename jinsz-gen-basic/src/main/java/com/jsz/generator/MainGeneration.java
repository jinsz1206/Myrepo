package com.jsz.generator;

import com.jsz.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @Title: MainGeneration
 * @Author jsz
 * @Package com.jsz.generator
 * @Date 2024/11/6 9:41
 * @description: 主生成方法
 */
public class MainGeneration {


    /**
     *
     * @param model 模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doMainGeneration(Object model) throws TemplateException, IOException {


        //静态生成
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        File parentFile = new File(projectPath).getParentFile();
        System.out.println(parentFile.getAbsolutePath());
        String inputPath = new File(parentFile, "jinsz-demo/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        StaticGeneration.copyFilesbyhutool(inputPath, outputPath);

        //动态生成;
        String inputPathDynamic = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPathDynamic = projectPath + File.separator + "MainTemplate.java";
        DynamicGeneration.DoGenerate(inputPathDynamic, outputPathDynamic, model);




    }

    public static void main(String[] args) throws TemplateException, IOException {

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("懒狗");
        mainTemplateConfig.setOutputText("结果为");
        mainTemplateConfig.setLoop(false);
        doMainGeneration(mainTemplateConfig);

    }
}
