/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author szaiii
 */
public class ConnectionDB {
    private static String USERNAME = "root";
    private static String PASSWORD = "Lostvyne04!";
    private static String DBNAME = "pbo2_2310010358";
    private static String URL = "jdbc:mysql://localhost/" + DBNAME;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registration Successful");
        } catch (ClassNotFoundException except) {
            throw new RuntimeException("Driver Not Found");
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);      
    }
}
