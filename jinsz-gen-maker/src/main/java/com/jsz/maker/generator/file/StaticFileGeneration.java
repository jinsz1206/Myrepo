package com.jsz.maker.generator.file;

import cn.hutool.core.io.FileUtil;

import java.io.File;


public class StaticFileGeneration {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        System.out.println(parentFile.getAbsolutePath());
        String inputPath = new File(parentFile, "jinsz-demo/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        copyFilesbyhutool(inputPath, outputPath);

    }


    public static void copyFilesbyhutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);

    }


}
