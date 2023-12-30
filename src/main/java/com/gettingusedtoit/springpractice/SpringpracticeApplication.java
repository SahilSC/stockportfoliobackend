package com.gettingusedtoit.springpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jep.*;
import jep.MainInterpreter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


@SpringBootApplication()
public class SpringpracticeApplication {

    public static void main(String[] args) {
        String pythonFolder = "/Users/sahils/PycharmProjects/stockproject/venv/lib/python3.11/site-packages";
        String jepPath = pythonFolder + "/jep/libjep.jnilib";
        MainInterpreter.setJepLibraryPath(jepPath);
        jep.JepConfig jepConf = new JepConfig();
        jepConf.addIncludePaths("/Users/sahils/PycharmProjects/stockproject/");
        jepConf.addIncludePaths(pythonFolder);
        SharedInterpreter.setConfig(jepConf);
        SpringApplication.run(SpringpracticeApplication.class, args);
    }

}
