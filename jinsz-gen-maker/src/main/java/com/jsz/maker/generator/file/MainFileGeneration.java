package com.jsz.maker.generator.file;

import com.jsz.maker.model.DataModel;
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
public class MainFileGeneration {


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
        StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);

        //动态生成;
        String inputPathDynamic = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPathDynamic = projectPath + File.separator + "MainTemplate.java";
        DynamicFileGeneration.DoGenerate(inputPathDynamic, outputPathDynamic, model);




    }

    public static void main(String[] args) throws TemplateException, IOException {

        DataModel dataModel = new DataModel();
        dataModel.setAuthor("懒狗");
        dataModel.setOutputText("结果为");
        dataModel.setLoop(false);
        doMainGeneration(dataModel);

    }
}
