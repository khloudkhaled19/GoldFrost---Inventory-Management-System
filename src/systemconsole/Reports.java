
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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
//import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;


public class Reports {

    private static String clientName, item, incomeCode, outcomeCode;
    private static double  income, outcome, price, credit;
    private static  String date, manualDate;
    public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";
    
    // report r = new report();
    public static ObservableList<report> dailyReports(String day, String month, String year){
        ObservableList<report> list = FXCollections.observableArrayList();
      try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);
         String inventory = "SELECT * FROM المخازن WHERE DAY(manualDate) = '" + day + "' AND MONTH(manualDate) ='" + month + "' AND YEAR(manualDate)='"+year+"'ORDER BY clientName , manualDate";

            PreparedStatement ps = con.prepareStatement(inventory);
           //  String inventory = "SELECT * FROM المخازن WHERE CAST( manualDate AS DATE) ='" + day + "' AND ORDER BY clientName , manualDate";
            //String inventory = "SELECT * FROM المخازن";
            ResultSet rs = ps.executeQuery();
           System.out.println("اسم العميل     اسم البضاعة       التاريخ      الكمية      السعر");

            while (rs.next()) {
                clientName = rs.getString("clientName");
                item = rs.getString("item");
                manualDate = rs.getString("manualDate");
                credit = rs.getDouble("credit");
                price = rs.getDouble("price");
                clientName = clientName.replaceAll("_", " ").toLowerCase();
            System.out.println(clientName+"    "+item+"   "+manualDate+"    "+credit+"   "+price);
                list.add(new report(clientName,item, manualDate, credit, price));
            }
            
        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        
       return list; 
    }

    public static ObservableList<report> MonthlyReports(String month, String year){
        ObservableList<report> list = FXCollections.observableArrayList();
        try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);

            Statement stmt = con.createStatement();
            // test case for showing'

            String inventory = "SELECT * FROM المخازن WHERE MONTH(manualDate) ='" + month + "' AND YEAR(manualDate)='"+year+"'ORDER BY clientName , manualDate";
            ResultSet rs = stmt.executeQuery(inventory);
         System.out.println("اسم العميل     اسم البضاعة        التاريخ          الكمية      السعر");

            while (rs.next()) {
                clientName = rs.getString("clientName");
                item = rs.getString("item");
                manualDate = rs.getString("manualDate");
                credit = rs.getDouble("credit");
                price = rs.getDouble("price");
                clientName = clientName.replaceAll("_", " ").toLowerCase();
                System.out.println(clientName+"    "+item+"   "+manualDate+"    "+credit+"   "+price);
                 list.add(new report(clientName,item, manualDate, credit, price));
            }

        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return list;
    }

    public static ObservableList<report> YearlyReports(String year){
        ObservableList<report> list = FXCollections.observableArrayList();
        try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);

            Statement stmt = con.createStatement();
            // test case for showing'

            String inventory = "SELECT * FROM المخازن WHERE YEAR(manualDate) ='" + year + "'ORDER BY clientName , manualDate";
            ResultSet rs = stmt.executeQuery(inventory);
         System.out.println("اسم العميل     اسم البضاعة        التاريخ         الكمية      السعر");

            while (rs.next()) {
                clientName = rs.getString("clientName");
                item = rs.getString("item");
                manualDate = rs.getString("manualDate");
                credit = rs.getDouble("credit");
                price = rs.getDouble("price");
                
                clientName = clientName.replaceAll("_", " ").toLowerCase();
                System.out.println(clientName+"    "+item+"   "+manualDate+"    "+credit+"   "+price);
                list.add(new report(clientName,item, manualDate, credit, price));
            }

        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return list;
    }

    public static ObservableList<report> incomeReports(String clientName){
                ObservableList<report> list = FXCollections.observableArrayList();

                try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);

            Statement stmt = con.createStatement();
            // test case for showing'

            String inventory = "SELECT clientName, item, income, incomeCode, credit, price, manualDate FROM الاذون WHERE clientName = '"+clientName+"' AND income != 0.0 ";
            ResultSet rs = stmt.executeQuery(inventory);
         System.out.println("اسم العميل     اسم البضاعة        الوارد       اذن الوارد         الكمية      السعر    التاريخ");

            while (rs.next()) {
               
                clientName = rs.getString("clientName");
                item = rs.getString("item");
                income = rs.getDouble("income");
                incomeCode = rs.getString("incomeCode");
                credit = rs.getDouble("credit");
                price = rs.getDouble("price");
                manualDate = rs.getString("manualDate");

                
                clientName = clientName.replaceAll("_", " ").toLowerCase();
                System.out.println(clientName+"    "+item+"   "+income+"   "+incomeCode+"   "+credit+"    "+price+"   "+manualDate);
                list.add(new report(clientName, item, income, incomeCode, credit, price, manualDate));
             }

        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return list;
    }

    public static ObservableList<report> outcomeReports(String clientName){
         ObservableList<report> list = FXCollections.observableArrayList();

                       try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);

            Statement stmt = con.createStatement();
            // test case for showing'

            String inventory = "SELECT clientName, item, outcome, outcomeCode, credit, price, manualDate FROM الاذون WHERE clientName = '"+clientName+"' AND outcome != 0.0 ";
            ResultSet rs = stmt.executeQuery(inventory);
         System.out.println("اسم العميل     اسم البضاعة        الصادر       اذن الصادر         الكمية      السعر    التاريخ");

            while (rs.next()) {
                clientName = rs.getString("clientName");
                item = rs.getString("item");
                outcome = rs.getDouble("outcome");
                outcomeCode = rs.getString("outcomeCode");
                credit = rs.getDouble("credit");
                price = rs.getDouble("price");
                manualDate = rs.getString("manualDate");

                
                clientName = clientName.replaceAll("_", " ").toLowerCase();
                list.add(new report(clientName, item, outcome, outcomeCode, credit, price, manualDate));
                System.out.println(clientName+"    "+item+"   "+outcome+"   "+outcomeCode+"   "+credit+"    "+price+"   "+manualDate);
            }

        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return list;
    }
}
