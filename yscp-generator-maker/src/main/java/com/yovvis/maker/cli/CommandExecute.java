package com.yovvis.maker.cli;

import com.yovvis.maker.cli.command.ConfigCommand;
import com.yovvis.maker.cli.command.GenerateCommand;
import com.yovvis.maker.cli.command.ListCommand;
import com.yovvis.maker.utils.CommandUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 客户端（遥控器）
 *
 * @author yovvis
 * @date 2023/11/25
 */
@Command(name = "ys", mixinStandardHelpOptions = true)
public class CommandExecute implements Runnable {
    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this).addSubcommand(new ConfigCommand()).addSubcommand(new GenerateCommand())
            .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体命令，或者请输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     *
     * @param args
     * @return
     */
    public Integer doExecute(String[] args) {
        // 强制交互
        args = CommandUtils.preCommand(args);
        return commandLine.execute(args);
    }
}