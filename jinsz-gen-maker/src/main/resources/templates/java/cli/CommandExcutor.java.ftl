package ${basePackage}.cli;
import ${basePackage}.cli.command.ConfigCommand;
import ${basePackage}.cli.command.GeneratorCommand;
import ${basePackage}.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;





@Command(name = "${name}" , mixinStandardHelpOptions = true)
public class CommandExcutor implements Runnable {

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GeneratorCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }


    @Override
    public void run() {
        //不输入命令时，给出提示
        System.out.println("请输入有效命令 ，或输入 --help 来获取帮助");

    }


    public Integer doExcute(String[] args) {
        return commandLine.execute(args);

    }



}
