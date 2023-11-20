package com.yovvis.cli.example;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

/**
 * pico cli用例
 *
 * @author yovvis
 */
@Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {

    @Option(names = {"-f", "--front-size"}, description = "Front Size")
    int frontSize = 21;

    @Parameters(paramLabel = "<word>", defaultValue = "Hello, pico cli", description = "Words to be translated into " +
            "ASCII art.")
    private String[] words = {"Hello,", " pico cli"};

    @Override
    public void run() {
        // 自己业务逻辑
        System.out.println("frontSize" + frontSize);
        System.out.println("words= " + String.join(",", words));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }


}
