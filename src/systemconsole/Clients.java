
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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Timestamp;



public class Clients {

    public static String clientName,clientItem, startDate, tableName;
    public static Date start;
    public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";

    //The end date has to be flexible calculated according to the client request over the inventory period

    public Clients(){} //constructor

   // public static  ArrayList<String> ClientsList  = new ArrayList<String>();
    /**
    public static void CreateClientsList(String tableName)throws Exception{
        tableName = tableName.toLowerCase();
        tableName = tableName.replaceAll("\\s","");
        String sqlStatement="CREATE TABLE " +tableName+ " ("
                + "clientName VARCHAR(1000) NOT NULL"
                + " )";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement s = con.prepareStatement(sqlStatement);
        s.executeUpdate();
        System.out.println("List created");
    }

    public static void addToClientsList(String clientName, String tableName) throws Exception{
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        String insertAll = "INSERT INTO" +" "+ tableName + "(`clientName`) "
                +"VALUES ('"+clientName+"')";

        PreparedStatement preInsert = con.prepareStatement(insertAll) ;
        preInsert.executeUpdate();

        System.out.println("client added to the list successfully");
    }
    * */

    public static void newClient(String clientName) throws Exception
    {
        clientName = clientName.toLowerCase();
        clientName = clientName.replaceAll("\\s","");
        items client = new items();

        client.createItemTable(clientName);


        System.out.println("Client is successfully created");

    }

    public static void deleteClient(String clientName) throws Exception
    {
        clientName = clientName.toLowerCase();
        clientName = clientName.replaceAll("\\s","");

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        Statement delClient = con.createStatement();
        
        delClient.addBatch(
             "DROP TABLE "+ clientName
        );
        
        delClient.addBatch(
             "DELETE FROM المخازن WHERE ClientName ='"+clientName+"' "
        );
        
        int [] results = delClient.executeBatch();

        System.out.println("Table is deleted");


    }








}