package com.jsz.generator;

import cn.hutool.core.io.FileUtil;
import lombok.val;

import java.io.File;

public class StaticGeneration {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        System.out.println(parentFile.getAbsolutePath());
        String inputPath = new File(parentFile, "jinsz-demo/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        copyFiles(inputPath, outputPath);

    }


    public static void copyFilesbyhutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);

    }



    public static void


}


