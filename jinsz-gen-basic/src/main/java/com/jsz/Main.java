package com.jsz;
import com.jsz.cli.CommandExcutor;

public class Main {

    public static void main(String[] args) {
        args = new String[]{"Generator","-l","-a","-o"};
        CommandExcutor commandExcutor = new CommandExcutor();
        commandExcutor.doExcute(args);
    }
}
