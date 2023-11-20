package com.yovvis.cli.example;

import java.util.concurrent.Callable;

/**
 * @author yovvis
 */
public class Login implements Callable<Integer> {


    String username;
    String password;
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println();
        return null;
    }
}
