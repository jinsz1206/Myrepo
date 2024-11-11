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



        String inputRootPath = "E:/JetBrains/jinszOne/jinsz-demo/acm-template-pro";
        String outputRootPath = "E:/JetBrains/jinszOne";

        String inputPath;
        String outputPath;

        //生成MainTemplate.java
        inputPath = new File(inputRootPath,"src/com/jsz/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/jsz/acm/MainTemplate.java").getAbsolutePath();
        DynamicFileGeneration.DoGenerate(inputPath, outputPath, model);

        //生成.gitignore
        inputPath = new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);

        //生成README.md
        inputPath = new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
        StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);




    }

}


//    public static void main(String[] args) throws TemplateException, IOException {
//
//        DataModel dataModel = new DataModel();
//        dataModel.setAuthor("懒狗");
//        dataModel.setOutputText("结果为");
//        dataModel.setLoop(false);
//        doMainGeneration(dataModel);
//
//    }