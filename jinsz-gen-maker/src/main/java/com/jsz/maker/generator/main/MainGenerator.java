package com.jsz.maker.generator.main;


import com.jsz.maker.meta.Meta;
import freemarker.template.TemplateException;


import java.io.IOException;


public class MainGenerator extends GeneratorTemplate{

    @Override
    protected void buildDist(String outputPath, String shellOutputFilePath, String sourceCopyPath, String jarPath) {
        System.out.println("不生成dist");
    }

    @Override
    protected String jarBuild(String outputPath, Meta meta) throws InterruptedException, IOException {
        return null;
    }

    @Override
    protected String BuildScript(String outputPath, String jarPath) throws IOException {
        return null;
    }

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator generator = new MainGenerator();
        generator.doGenerate();
    }
}
