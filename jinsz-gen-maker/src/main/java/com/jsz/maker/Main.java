package com.jsz.maker;
import com.jsz.maker.cli.CommandExcutor;

public class Main {

    public static void main(String[] args) {
//        args = new String[]{"list"};
        CommandExcutor commandExcutor = new CommandExcutor();
        commandExcutor.doExcute(args);
    }
}