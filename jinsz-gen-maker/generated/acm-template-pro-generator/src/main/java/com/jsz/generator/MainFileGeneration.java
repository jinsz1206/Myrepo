package com.jsz.generator;
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
        String outputRootPath = "generated";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath,"src/com/jsz/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/jsz/acm/MainTemplate.java").getAbsolutePath();
        DynamicFileGeneration.DoGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);

        inputPath = new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
        StaticFileGeneration.copyFilesbyhutool(inputPath, outputPath);




    }
    
}

