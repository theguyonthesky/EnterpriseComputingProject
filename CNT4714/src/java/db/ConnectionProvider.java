package db;

import java.sql.Connection;
import java.sql.DriverManager;

/*
Name: Elmaddin Azizli
Class: CNT:4714, Project 4
Date: 04/20/2022
*/
public class ConnectionProvider {

    private static Connection con;

    public static Connection getConnection() {
        try {

            if (con == null) {
                //driver class load
                Class.forName("com.mysql.jdbc.Driver");

                //create a connection..
                con = DriverManager.
                        getConnection("jdbc:mysql://localhost:3306/project4", "root", "");
                System.out.println("Connection Created");

            }

        } catch (Exception e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return con;
    }

}
