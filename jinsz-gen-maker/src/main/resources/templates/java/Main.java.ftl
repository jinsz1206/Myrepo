package ${basePackage};
import ${basePackage}.cli.CommandExcutor;

public class Main {

    public static void main(String[] args) {
        CommandExcutor commandExcutor = new CommandExcutor();
        commandExcutor.doExcute(args);
    }
}
