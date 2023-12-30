package com.gettingusedtoit.springpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jep.*;
import jep.MainInterpreter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


//@SpringBootApplication()
public class PythonInterpreter {

    SharedInterpreter subInterp = null;
    public PythonInterpreter() {
        subInterp = new SharedInterpreter();

    }

    public String getStockDataFromPython() {
        subInterp.eval("import main as p");
        return subInterp.getValue("p.getStockData()").toString();
    }

    public String getStockDataFromPythonSQL() {
        subInterp.eval("import main as main");
        return subInterp.getValue("main.get_data_from_SQL_server()").toString();
    }


    public static void main(String[] args) {
        PythonInterpreter pi = new PythonInterpreter();
        String str = pi.getStockDataFromPython();
        System.out.println(str);
    }
//    public static void main(String[] args) {
////        SpringApplication.run(SpringpracticeApplication.class, args);
////        String pythonFolder = System.getenv("DYLD_LIBRARY_PATH");
//
//
//        String pythonFolder = "/Users/sahils/PycharmProjects/stockproject/venv/lib/python3.11/site-packages";
//        //define the JEP library path
//        String jepPath = pythonFolder + "/jep/libjep.jnilib";
//
////        if (!Files.exists(Path.of(jepPath))){
////           jepPath = pythonFolder + "/jep/libjep.so";
////        }
//        //initialize the MainInterpreter
//        MainInterpreter.setJepLibraryPath(jepPath);
//
//
//        jep.JepConfig jepConf = new JepConfig();
//
//        jepConf.addIncludePaths("/Users/sahils/PycharmProjects/stockproject/");
//
//        jepConf.addIncludePaths(pythonFolder);
//
//        SharedInterpreter.setConfig(jepConf);
//
//        SharedInterpreter subInterp = new SharedInterpreter();
//
//        subInterp.eval("import main as p");
//
////        subInterp.eval("st1 = p.getStockData()");
//        System.out.println(subInterp.getValue("p.getStockData()"));
////        System.out.println(subInterp.getValue(p));
////        System.out.println(subInterp.getValue("res_spacy"));
////        System.out.println(subInterp.getValue("p.mult(1,1203242)"));
//
//
//
////        String pythonFolder = "/Users/sahils/PycharmProjects/stockproject/venv/lib/python3.11/site-packages";
////        //define the JEP library path
////        String jepPath = pythonFolder + "/jep/libjep.jnilib";
////        // set path for jep executing python3.9
////        MainInterpreter.setJepLibraryPath(jepPath);
////
////        // set path for python docs with python script to run
////        jep.JepConfig jepConf = new JepConfig();
////        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");
////
////        //create the interpreter for python executing
////        Interpreter subInterp = jepConf.createSubInterpreter();
////
////                //import  .py doc with to run
////        subInterp.eval("import python_functions as p");
////
////        // run each function from the .py doc I
////        subInterp.eval("a = p.hi()");
//
//
//    }

}
