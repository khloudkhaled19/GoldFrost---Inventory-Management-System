
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import goldfrosttest.LoginFXMLController;

public class Employees {
    public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";
    
    public static String firstName, lastName, phone, jobTitle, email, Password, confpassword,gender;
    
    public static void updatefirstName(String firstName) throws SQLException{ 
       String NoldValue = LoginFXMLController.user.get(LoginFXMLController.user.keySet().toArray()[0]);
       String PoldValue = LoginFXMLController.user.keySet().toString();
       PoldValue = PoldValue.replaceAll("[\\[\\](){}]","");
     //  String PoldValue = LoginFXMLController.user.get(0);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        String stmt = "UPDATE الموظفون SET firstName ='"+firstName+"' WHERE firstName ='"+NoldValue+"' AND password = '"+PoldValue+"' ";
        PreparedStatement statement = con.prepareStatement(stmt);
        statement.executeUpdate();
        System.out.print("updated successfully");
    }
        public static void updatelastName(String lastName) throws SQLException{ 
       String NoldValue = LoginFXMLController.user.get(LoginFXMLController.user.keySet().toArray()[0]); 
       String PoldValue = LoginFXMLController.user.get(LoginFXMLController.user.values().toArray()[0]);
       
          try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        String stmt = "UPDATE الموظفون SET lastName ='"+lastName+"' WHERE firstName ='"+NoldValue+"' AND password = '"+PoldValue+"' ";
        PreparedStatement statement = con.prepareStatement(stmt);
        statement.executeUpdate();
        System.out.print("updated successfully");
    }
       public static void updatephone(String phone) throws SQLException{ 
       String NoldValue = LoginFXMLController.user.get(LoginFXMLController.user.keySet().toArray()[0]);
       String PoldValue = LoginFXMLController.user.get(LoginFXMLController.user.values().toArray()[0]);
       
          try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        String stmt = "UPDATE الموظفون SET phone ='"+phone+"' WHERE firstName ='"+NoldValue+"' AND password = '"+PoldValue+"' ";
        PreparedStatement statement = con.prepareStatement(stmt);
        statement.executeUpdate();
        System.out.print("updated successfully");
    } 
    public static void updateEmail(String email) throws SQLException{ 
       String NoldValue = LoginFXMLController.user.get(LoginFXMLController.user.keySet().toArray()[0]);
       String PoldValue = LoginFXMLController.user.get(LoginFXMLController.user.values().toArray()[0]);
       
          try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        String stmt = "UPDATE الموظفون SET email ='"+email+"' WHERE firstName ='"+NoldValue+"' AND password = '"+PoldValue+"' ";
        PreparedStatement statement = con.prepareStatement(stmt);
        statement.executeUpdate();
        System.out.print("updated successfully");
    }
    public static void updatepassword(String Password) throws SQLException{ 
       String NoldValue = LoginFXMLController.user.get(LoginFXMLController.user.keySet().toArray()[0]);
       String PoldValue = LoginFXMLController.user.get(LoginFXMLController.user.values().toArray()[0]);
        
        
          try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        String stmt = "UPDATE الموظفون SET password ='"+Password+"' WHERE firstName ='"+NoldValue+"' AND password = '"+PoldValue+"' ";
        PreparedStatement statement = con.prepareStatement(stmt);
        statement.executeUpdate();
        System.out.print("updated successfully");
    }
    public static void deleteEmployee(String email) throws SQLException{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);

        String selectAll = "SELECT * FROM الموظفون";

        PreparedStatement statement = con.prepareStatement(selectAll);
        ResultSet result = statement.executeQuery();

        //check if item exists
        
        boolean flag = false;
       
        while(result.next()){
            if(email.equalsIgnoreCase(result.getString("email"))){
                flag = true;
                break;
            }
            else{
                flag = false;
            }
        }
        
           PreparedStatement statement2 = con.prepareStatement("DELETE FROM الموظفون WHERE email = '"+email+"'");
           statement2.executeUpdate();

            System.out.println("Successfully deleted");
 
    }
    
    public static ObservableList<employee> showEmployees(){
          ObservableList<employee> list = FXCollections.observableArrayList();
         try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);

            Statement stmt = con.createStatement();
            // test case for showing'

            String inventory = "SELECT * FROM الموظفون";
            ResultSet rs = stmt.executeQuery(inventory);
         System.out.println("اسم الموظف    رقم التليفون       الايميل      المهنة ");

            while (rs.next()) {
               // String Name = rs.getString("firstName" +" "+ "lastName");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                phone = rs.getString("phone");
                email = rs.getString("email");
                jobTitle = rs.getString("jobTitle");
                
                String Name = firstName + " " + lastName;
                list.add(new employee(Name,phone, email, jobTitle));
                System.out.println(Name+"    "+phone+"   "+email+"    "+jobTitle);
            }

        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return list;
    }
}
