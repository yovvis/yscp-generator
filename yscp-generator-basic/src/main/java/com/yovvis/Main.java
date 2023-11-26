package com.yovvis;

import com.yovvis.cli.CommandExecute;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // args = new String[] {"generate", "-l", "-a", "-o"};
        // args = new String[] {"config"};
        // args = new String[] {"--list"};
        // args = new String[] {"--help"};
        CommandExecute execute = new CommandExecute();
        execute.doExecute(args);
    }
}