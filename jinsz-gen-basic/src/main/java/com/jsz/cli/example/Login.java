package com.jsz.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;


//@CommandLine.Command(subcommands = {ASCIIArt.class,})
public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--password"}, description = "Passphrase", arity = "0..1",interactive = true, prompt = "密码提示")
    String password;

    @Option(names = {"-cp", "--CheckPassword"}, description = "Check Password",arity = "0..1",interactive = true,prompt = "确认")
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("CheckPassword = " + checkPassword);

        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "懒狗", "-p","-cp","123");
    }
}
