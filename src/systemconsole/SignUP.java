
package systemconsole;

import javax.management.StandardMBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.ObjectInputStream.GetField;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;
import java.sql.DatabaseMetaData;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import goldfrosttest.LoginFXMLController;
import goldfrosttest.RegistrationFXMLController;

public class SignUP {
   
    public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String firstName, lastName, phone, jobTitle, email, Password, confpassword,gender;
   
        public static void createEmployeesTable() throws Exception{

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "الموظفون", null);

        if (tables.next()) {
            //if table exists do nothing

        }
        else {
            // Table does not exist
            String sqlStatement="CREATE TABLE الموظفون ("
                    + "firstName VARCHAR(1000) NOT NULL,"
                    + "lastName VARCHAR(1000) NOT NULL,"
                    + "phone VARCHAR(1000) NOT NULL,"
                    + "email VARCHAR(1000) NOT NULL,"
                    + "jobTitle VARCHAR(1000) NOT NULL,"
                  //  + "gender VARCHAR(1000) NOT NULL,"
                    + "password VARCHAR(1000) NOT NULL"
                    + " )";

            PreparedStatement s = con.prepareStatement(sqlStatement);
            s.executeUpdate();

        }

    }
        
    public static void Registration(String firstName, String lastName, String phone, String email, String jobTitle, String Password, String confpassword) throws SQLException{
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUP.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        if(Password.equals(confpassword)){
            
            String signUp = "INSERT INTO الموظفون (`firstName`,`lastName`, `phone`, `email`, `jobTitle`,  `password`)"
                           + "VALUES ('"+firstName+"','"+lastName+"','"+phone+"','"+email+"','"+jobTitle+"','"+Password+"')";
            
            PreparedStatement statement = con.prepareStatement(signUp);
            
            statement.executeUpdate();
            
            
            System.out.print("user added successfully");

        }
        else{
               System.out.print("please check your password ");

        }
        
    }  
    
    public static void Login(String firstName, String Password) throws SQLException{
        
       String admin = "ادمن";
       String accountant = "محاسب";
       String labror = "عامل";
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUP.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        Statement statement = con.createStatement();
 
        ResultSet results = statement.executeQuery("SELECT * FROM الموظفون  WHERE firstName = '"+firstName+"' AND password ='"+Password+"' ");
        if(results.next()){
            firstName = results.getString("firstName");
             //lastName = results.getString("lastName");
            System.out.println("welcome "+firstName+ " to the system " );
        
         if(admin.equals(results.getString("jobTitle"))){
            
          System.out.println("welcome to the admin panel" );
         }
         else if(accountant.equals(results.getString("jobTitle"))){
         
          System.out.println("welcome to the accountant panel" );
         }
          else if(labror.equals(results.getString("jobTitle"))){
         
          System.out.println("welcome to the labror panel" );
         }


        }
        else{
            System.out.println("the user doesn't exist");
        }

    }
    public static void logout(){
        LoginFXMLController.user.clear();
    }
    
    
}
