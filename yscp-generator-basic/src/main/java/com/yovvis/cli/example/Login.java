package com.yovvis.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 * @author yovvis
 */
public class Login implements Callable<Integer> {


    @Option(names = {"-u", "--username"}, description = "User Name")
    String username;
    @Option(names = {"-p", "--password"}, description = "Password", echo = false, arity = "0..1", prompt = "请输入密码：", interactive = true)
    String password;
    @Option(names = {"-cp", "--checkpassword"}, description = "Check Password", arity = "0..1", echo = true, prompt = "请确认密码：", interactive = true)
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("password=" + password);
        System.out.println("checkPassword=" + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        // 弱交互加上arity="0..1"
        // 利用反射强交互
        int exitCode = new CommandLine(new Login()).execute("-u", "yovvis", "-p", "xxx", "-cp");
        System.exit(exitCode);
    }

    // todo 根据反射获取强制交互
    public static String[] getDecison(String[] args) {
        return null;
    }
}
