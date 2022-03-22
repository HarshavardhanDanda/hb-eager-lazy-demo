package testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Testjdbc {
    public static void main(String[] args){
        String jdbcUrl="jdbc:mysql://localhost:3306/hb-03-one-to-many?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user= "hbstudent";
        String pass="Harsha@076";

        try{
            System.out.println("Connecting to database: "+jdbcUrl);
            Connection myConn= DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection Successful!!!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


