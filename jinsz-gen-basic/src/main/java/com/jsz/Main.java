package com.jsz;
import com.jsz.cli.CommandExcutor;

public class Main {

    public static void main(String[] args) {
//        args = new String[]{"list"};
        CommandExcutor commandExcutor = new CommandExcutor();
        commandExcutor.doExcute(args);
    }
}
