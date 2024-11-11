package com.jsz.maker.generator.main;


import freemarker.template.TemplateException;


import java.io.IOException;


public class MainGenerator extends GeneratorTemplate{

    @Override
    protected void buildDist(String outputPath, String shellOutputFilePath, String sourceCopyPath, String jarPath) {
        System.out.println("不生成dist");
    }

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator generator = new MainGenerator();
        generator.doGenerate();
    }
}
