package com.jsz.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * @Title: ListCommand
 * @Author jsz
 * @Package com.jsz.cli.command
 * @Date 2024/11/6 17:28
 * @description:
 */

@CommandLine.Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");

        File parentFile = new File(projectPath).getParentFile();

        String inputPath = new File(parentFile, "jinsz-demo/acm-template").getAbsolutePath();

        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }


    }
}
