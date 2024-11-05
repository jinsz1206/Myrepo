package com.jsz.generator;

import com.jsz.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Title: DynamicGeneration
 * @Author jsz
 * @Package com.jsz.generator
 * @Date 2024/11/5 16:10
 * @description: 动态代码生成
 */


public class DynamicGeneration {
    public static void main(String[] args) throws IOException, TemplateException {

        String projectPath = System.getProperty("user.dir");
        System.out.printf(projectPath);
        String inputPath = projectPath + File.separator + "jinsz-gen-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("lanbi");
        mainTemplateConfig.setOutputText("最后的结果");
        mainTemplateConfig.setLoop(false);
        DoGenerate(inputPath, outputPath, mainTemplateConfig);


    }


    /**
     *
     * @param inputPath 模板文件输入路径
     * @param outputPAth 模板文件输出路径
     * @param model 模板模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void DoGenerate(String inputPath,String outputPAth,Object model) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateFile = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateFile);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");


        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);


        Writer out = new FileWriter(outputPAth);
        template.process(model,out);
        out.close();

    }

}
