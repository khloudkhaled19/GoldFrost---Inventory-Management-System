
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
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
  public static String clientName, item, incomeCode, outcomeCode;
  public static Double  income, outcome, price, credit;
  public static  String date, manualDate;
  public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";
    
    public Inventory(){}
    
        
        public static void createInventoryTable() throws Exception{

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "المخازن", null);

        if (tables.next()) {
            //if table exists do nothing

        }
        else {
            // Table does not exist
            String sqlStatement="CREATE TABLE المخازن ("
                    + "clientName VARCHAR(1000) NOT NULL,"
                    + "item VARCHAR(1000) NOT NULL,"
                    + "credit DOUBLE,"
                  //  + "weight DOUBLE NOT NULL,"
                    + " price DOUBLE,"
                    + "date TIMESTAMP,"
                    +"manualDate TIMESTAMP"
                    + " )";

            PreparedStatement s = con.prepareStatement(sqlStatement);
            s.executeUpdate();

        }

    }
    
        public static void createIncomeOutcomeCodesTable() throws Exception{

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "الاذون", null);

        if (tables.next()) {
            //if table exists do nothing

        }
        else {
            // Table does not exist
            String sqlStatement="CREATE TABLE الاذون("
                    + "clientName VARCHAR(1000) NOT NULL,"
                    + "item VARCHAR(1000) NOT NULL,"
                    + "income DOUBLE,"
                    + "incomeCode VARCHAR(1000),"
                    + "outcome DOUBLE,"
                    + "outcomeCode VARCHAR(1000),"
                    + "credit DOUBLE,"
                  //  + "weight DOUBLE NOT NULL,"
                    + "price DOUBLE,"
                    + "date TIMESTAMP,"
                    +"manualDate TIMESTAMP"
                    + " )";

            PreparedStatement s = con.prepareStatement(sqlStatement);
            s.executeUpdate();

        }

    }    
    
    // every client table
    public static ObservableList<inventories> ShowclientInventory(String tableName){
         ObservableList<inventories> list = FXCollections.observableArrayList();
             try {
         Class.forName(driver);
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }
      try {
          Connection con = DriverManager.getConnection(url,username,password);
         
         Statement stmt = con.createStatement();
         // test case for showing
          String ShowClientItems =  "SELECT * FROM '"+tableName+"'";
         ResultSet rs = stmt.executeQuery(ShowClientItems);
         System.out.println("اسم العميل     اسم البضاعة     الوارد      اذن رقم الوارد      الصادر      اذن رقم الصادر      التاريخ     التاريخ اليدوى      الكمية      السعر");
         
         while (rs.next()) {
            tableName = rs.getString("clientName");
            item = rs.getString("item");
           // weight = rs.getDouble("weight");
            income = rs.getDouble("income");
            incomeCode = rs.getString("incomeCode");
            outcome = rs.getDouble("outcome");
            date = rs.getString("date");
            manualDate = rs.getString("manualDate");
            credit = rs.getDouble("credit");
            price = rs.getDouble("price");
            tableName = tableName.replaceAll("_", "\\s+").toLowerCase();
            list.add(new inventories(tableName, item, manualDate, incomeCode, price, credit, income,outcome ));
            System.out.println(tableName+"    "+item+"    "+income+"    "+outcome+"     "+date+"    "+manualDate+"    "+credit+"   "+price);
         }
         
         
      } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
      }
      return list;
   }
    //list of the clints names in the inventory
    public static void ShowClientsList(){
       
          try {
         Class.forName(driver);
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }
      try {
          Connection con = DriverManager.getConnection(url,username,password);
         
         Statement stmt = con.createStatement();
         // test case for showing
          String ShowClientItems =  "SELECT * FROM clientslist";
         ResultSet rs = stmt.executeQuery(ShowClientItems);
         System.out.println("clientName");
         
         while (rs.next()) {
            String Name = rs.getString("clientName");
            System.out.println(Name);
         }
        } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
        }
    }
    
    //all clients and items in inventory
    public static void ShowAllInventory(){
        
           try {
         Class.forName(driver);
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }
      try {
          Connection con = DriverManager.getConnection(url,username,password);
         
         Statement stmt = con.createStatement();
         // test case for showing
          String inventory =  "SELECT * FROM المخازن";
         ResultSet rs = stmt.executeQuery(inventory);
         System.out.println("اسم العميل     اسم البضاعة     الوارد      اذن رقم الوارد      الصادر      اذن رقم الصادر      التاريخ     التاريخ اليدوى      الكمية      السعر");
         
         while (rs.next()) {
            clientName = rs.getString("clientName");
            item = rs.getString("item");
          //  weight = rs.getDouble("weight");
         //   income = rs.getDouble("income");
         //   incomeCode = rs.getString("incomeCode");
         //   outcome = rs.getDouble("outcome");
         //   outcomeCode = rs.getString("outcomeCode");
            date = rs.getString("date");
            manualDate = rs.getString("manualDate");
            credit = rs.getDouble("credit");
            price = rs.getDouble("price");
            
            clientName = clientName.replaceAll("_", "\\s+").toLowerCase();
            System.out.println(clientName+"    "+item+"     "+date+"    "+manualDate+"    "+credit+"   "+price);
         }
         
      } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
      }
    }
    
    
    
}


