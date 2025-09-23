package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Assurance";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Azzedine2004";
    public static Connection connection;
    public static Connection getConnection(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
                System.out.println("Connection On ");
            }catch (SQLException E){
                System.out.println(E.getMessage());
            }
        }
        return connection;
    }
}
