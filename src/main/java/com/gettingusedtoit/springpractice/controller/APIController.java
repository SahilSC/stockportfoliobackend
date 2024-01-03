package com.gettingusedtoit.springpractice.controller;

import com.gettingusedtoit.springpractice.PythonInterpreter;
import com.gettingusedtoit.springpractice.controller.model.StockData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
//allow cross origin requeusts
@CrossOrigin(origins = "*")
public class APIController {

    private static Connection connection = getConnection();;

    @PostMapping("register")
    public ResponseEntity<List<String>> register(@RequestBody Map<String, String> body) throws SQLException {
        System.out.println("correct");
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        System.out.println(email + " " + password);
        PreparedStatement check = connection.prepareStatement("SELECT * FROM stockdb.Users WHERE email = \"" + email + "\"");
        ResultSet res = check.executeQuery();
        if (res.next()) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(List.of("User exists"));
        }
        else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO stockdb.Users (email, password) VALUES (\"" +
                    email + "\", \"" + password + "\")");
            stmt.executeUpdate();
        }
//        ResultSet res =
//        ResultSet res = stmt.executeQuery();
        return ResponseEntity.ok(Arrays.asList("Hi", "Bob"));
    }

    @PostMapping("login")
    public ResponseEntity<List<String>> login(@RequestBody Map<String, String> body) throws SQLException {
        String email = (String) body.get("email");
        String password = (String) body.get("password");
        System.out.println(email + " " + password);
        PreparedStatement check = connection.prepareStatement("SELECT * FROM stockdb.Users WHERE email = \"" + email + "\"");
        ResultSet res = check.executeQuery();
        if (res.next()) {
            String truePassword = res.getString("password");
            if (truePassword.equals(password)) {
                return ResponseEntity.status(HttpStatus.OK).body(List.of("Success"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of("Wrong password"));
        }
        else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(List.of("User doesn't exist"));
        }
    }

    @PostMapping("student")
    public List<String> add() {
        return List.of("Added");
    }

    @PostMapping("getStock")
    public ResponseEntity<StockData> getOneStock(@RequestBody Map<String, Object> body) throws SQLException {
        String name = (String) body.get("name");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM stockdb.stockdata WHERE symbol = \'" + name + "\'");
        ResultSet res = stmt.executeQuery();
        if(res.next()) {
//        res.nex
//        System.out.println(re);
            String symbol = res.getString(2);
            double bidPrice = res.getDouble(3);
            int bidSize = res.getInt(4);
            double askPrice = res.getDouble(5);
            int askSize = res.getInt("askSize");
            StockData getStock = new StockData(symbol, bidPrice, bidSize, askPrice, askSize);
            return ResponseEntity.status(HttpStatus.OK).body(getStock);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        return "Hello";
    }

    //convert to json

    @GetMapping("stocks")
    public List<StockData> getStocks() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM stockdb.stockdata LIMIT 3");
        ResultSet res = stmt.executeQuery();
        ArrayList<StockData> arr = new ArrayList<>();
        while (res.next()) {
            String symbol = res.getString(2);
            double bidPrice = res.getDouble(3);
            int bidSize = res.getInt(4);
            double askPrice = res.getDouble(5);
            int askSize = res.getInt("askSize");
            arr.add(new StockData(symbol, bidPrice, bidSize, askPrice, askSize));
        }
//        StockData aapl = new StockData("AAPL", 10.1, 100, 10.3, 50);
//        StockData tsla = new StockData("TSLA", 301, 75, 303, 80);
//        StockData msft = new StockData("MSFT", 201, 100, 10.3, 50);
//        StockData amzn = new StockData("AMZN", 10.1, 100, 10.3, 50);
//        arr.add(aapl);
//        arr.add(tsla);
//        arr.add(msft);
//        arr.add(amzn);

        return arr;
    }


    private static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/stockdb?useSSL=false";
        String user = "root";
        String password = "04196150";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @GetMapping("pythonstocks")
    public String getPythonStock() {
//        ArrayList<String> arr = new ArrayList<>();
//        arr.add("hi");
//        arr.add("hey");
        System.out.println("Completed");
        PythonInterpreter pi = new PythonInterpreter();
        String stock = pi.getStockDataFromPython();
        return stock;
    }

    @GetMapping("pythonstockssql")
    public String getPythonStockFromSQL() {
//        ArrayList<String> arr = new ArrayList<>();
//        arr.add("hi");
//        arr.add("hey");
        System.out.println("Completed SQL");
        PythonInterpreter pi = new PythonInterpreter();
        String stock = pi.getStockDataFromPythonSQL();
        return stock;
    }

}
