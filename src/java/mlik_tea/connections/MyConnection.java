/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlik_tea.connections;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Loi Nguyen
 */
public class MyConnection implements Serializable {

    public static Connection getConnection() throws Exception {
        
        Connection conn;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1435;databaseName=Milk_Tea";
        conn = DriverManager.getConnection(url, "loi", "123456");

        return conn;
    }
}
