package com.yovvis.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 子命令示例
 *
 * @author yovvis
 * @date 2023/11/23
 */
@Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable {

    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @Command(name = "add", description = "增加", mixinStandardHelpOptions = true)
    static class AddCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行增加命令");
        }
    }

    @Command(name = "delete", description = "删除", mixinStandardHelpOptions = true)
    static class DeleteCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行删除命令");
        }
    }

    @Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class QueryCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行查间命令");
        }
    }

    public static void main(String[] args) {
        String[] myArgs = new String[] {"add"};
        int exitCode = new CommandLine(new SubCommandExample()).addSubcommand(new AddCommand())
            .addSubcommand(new DeleteCommand()).addSubcommand(new QueryCommand()).execute(myArgs);
        System.exit(exitCode);
    }

}