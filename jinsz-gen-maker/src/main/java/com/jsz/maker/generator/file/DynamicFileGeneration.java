package com.jsz.maker.generator.file;

import cn.hutool.core.io.FileUtil;
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
 * @Package com.jsz.generate
 * @Date 2024/11/5 16:10
 * @description: 动态代码生成
 */


public class DynamicFileGeneration {


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


        if (!FileUtil.exist(outputPAth)) {
            FileUtil.touch(outputPAth);
        }

        Writer out = new FileWriter(outputPAth);
        template.process(model,out);


        out.close();

    }

}
