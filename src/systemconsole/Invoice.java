
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
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Invoice {
    
 
    public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String itemName, incomeCode, outcomeCode,month,year;
    public static double clientName,itemPrice, income,outcome,credit,itemTotal;
    public static String date,manualDate;
    public static double value, taxes, totalCarriage ,itemCarriage, noTaxTotal, total, Tcarriage; //taxes = 14% of the total
    public static String carriageValue;

    public static void createInvoiceTable() throws Exception{

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "الفاتورة", null);

        if (tables.next()) {
            //if table exists do nothing

        }
        else {
            // Table does not exist
            String sqlStatement="CREATE TABLE الفاتورة ("
                    + "clientName VARCHAR(1000) NOT NULL,"
                    + "item VARCHAR(1000) NOT NULL,"
                    + "credit DOUBLE,"
                  //  + "weight DOUBLE NOT NULL,"
                    + "price DOUBLE,"
                    + "date TIMESTAMP,"
                    +"manualDate TIMESTAMP,"
                    +"carriage DOUBLE"
                    + " )";

            PreparedStatement s = con.prepareStatement(sqlStatement);
            s.executeUpdate();

        }

    }
 public static double calcCarriage(String month, String year, String clientName)throws Exception{

       String foundItem,uniqueItem;
       String foundClient;
       double carriageValue=1;
       double itemCarriage = 0.0;
       double totalCarriage = 0.0;
       double foundIncome = 0.0;

        Scanner sc = new Scanner(System.in);

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        String selectQuery = "SELECT * FROM "+clientName+" WHERE MONTH(manualDate)=? AND YEAR(manualDate)=? AND income IS NOT NULL AND income!=0";
        String selectItems = "SELECT item FROM الفاتورة WHERE clientName=? AND MONTH(manualDate)=? AND YEAR(manualDate)=?";
        String selectCarriage = "SELECT * FROM الفاتورة WHERE clientName=? AND MONTH(manualDate)=? AND YEAR(manualDate)=?";

        PreparedStatement statement = con.prepareStatement(selectQuery);
        statement.setString(1,month);
        statement.setString(2,year);
        ResultSet resultClient = statement.executeQuery();

        PreparedStatement statement2 = con.prepareStatement(selectItems);
        statement2.setString(1,clientName);
        statement2.setString(2,month);
        statement2.setString(3,year);
        ResultSet resultItem = statement2.executeQuery();

       PreparedStatement statement3 = con.prepareStatement(selectCarriage);
       statement3.setString(1,clientName);
       statement3.setString(2,month);
       statement3.setString(3,year);
       ResultSet resultCarriage = statement3.executeQuery();

       while(resultItem.next())
       {
           itemCarriage=0.0;
           uniqueItem = resultItem.getString(1);
          // System.out.println("\nCarriage Value for "+uniqueItem+": ");
          // carriageValue = sc.nextDouble();

           while (resultClient.next())
           {
               foundItem = resultClient.getString(1);
               foundIncome = resultClient.getDouble(2);

               //System.out.println(foundItem+" "+foundIncome);

               boolean areTwoStringsEqual = uniqueItem.equals(foundItem);

               if(areTwoStringsEqual){

                   if(resultCarriage.next()) {
                       carriageValue = resultCarriage.getDouble(7);
                   }

                   itemCarriage=itemCarriage+carriageValue*foundIncome;

               }

           }

           resultClient.beforeFirst();
           //System.out.format("%10s %30s %20s %20s", uniqueItem, foundIncome, carriageValue, itemCarriage);

           totalCarriage=totalCarriage+itemCarriage;

           System.out.println("Carriage for "+uniqueItem+" is "+itemCarriage);

           continue;


       }


       System.out.println("\nTotal Carriage for client "+clientName+" is "+totalCarriage);

       return totalCarriage;

   }

    //to delete calculated items
    public static void deleteCalculated(String tableName,String itemName) throws Exception{
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        String selectAll = "SELECT * FROM"+" "+tableName;

        PreparedStatement statement = con.prepareStatement(selectAll);
        ResultSet result = statement.executeQuery();

        //check if item exists
        boolean flag = false;
        while(result.next()){
            if(itemName.equalsIgnoreCase(result.getString(1))){
                flag = true;
                break;
            }
            else{
                flag = false;
            }
        }

        if(flag) {
            Statement deleteAll = con.createStatement();
            deleteAll.addBatch(
                    "DELETE FROM"+" "+tableName+" "+"WHERE item = '"+itemName+"'"
            );
            deleteAll.addBatch(
                    "DELETE FROM المخازن WHERE ClientName ='"+tableName+"' AND item = '"+itemName+"'"
            );
            deleteAll.addBatch(
                    "DELETE FROM الفاتورة WHERE ClientName ='"+tableName+"' AND item = '"+itemName+"'"
            );
            //  statement = con.prepareStatement("DELETE FROM"+" "+tableName+" "+"WHERE item = ?");
            //  statement.setString(1, itemName);
            //  statement.executeUpdate();
            int [] deleteResult = deleteAll.executeBatch();

        }
    }

    //to calc old items from previous months.
    public static double calcReserved(String clientName, String month, String year) throws Exception{
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);
        double reservedTotal=0.0;

        String selectMonth = "SELECT * FROM الفاتورة WHERE clientName=? AND MONTH(manualDate)!=? AND YEAR(manualDate)=?";
        PreparedStatement statement = con.prepareStatement(selectMonth);
        statement.setString(1,clientName);
        statement.setString(2,month);
        statement.setString(3,year);

        ResultSet resultMonth = statement.executeQuery();

        while (resultMonth.next()){
            itemName = resultMonth.getString(2);
            credit = resultMonth.getDouble(3);
            itemPrice = resultMonth.getDouble(4);
            manualDate = resultMonth.getString(6);
            reservedTotal = credit*itemPrice;

            System.out.format("%10s %30s %20s %20s", reservedTotal, credit, itemPrice, itemName);
            System.out.println();

           deleteCalculated(clientName,itemName);

        }

        return reservedTotal;

    }

    public static ObservableList<invoices> calcInvoice(String clientName,String month, String year) throws Exception{
        
            //  ObservableList<invoices> list1 = FXCollections.observableArrayList();
           //   ObservableList<invoices> list2 = FXCollections.observableArrayList();
           //        Iterator<invoices> iterator = list1.iterator();
              ObservableList<invoices> list = FXCollections.observableArrayList();

              

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);
        double totalCarriage = 0.0;
        double reserved = 0.0;


        /*create a invoice table to store client name with his all items only once and the credit,weight and price
          to calc the total in the end
         */

        /* value = price*credit
         *  input: clientname, date
         *  after done, display
         *  print invoice template   */

        //taxes = 0.14*noTaxTotal
        //total = value + carriageTotal + taxes

        //String selectMonth = "SELECT * FROM الفاتورة WHERE clientName=? AND DAY(manualDate)<15 AND MONTH(manualDate)=? AND YEAR(manualDate)=? AND manualDate >= DATE_SUB(NOW(), INTERVAL 6 MONTH)";
                //+"SELECT * FROM الفاتورة WHERE manualDate >= DATE_SUB(NOW(), INTERVAL 6 MONTH)";
        String selectMonth = "SELECT * FROM الفاتورة WHERE clientName=? AND DAY(manualDate)<15 AND MONTH(manualDate)=? AND YEAR(manualDate)=?";
        PreparedStatement statement = con.prepareStatement(selectMonth);
        statement.setString(1,clientName);
        statement.setString(2,month);
        statement.setString(3,year);

        ResultSet resultMonth = statement.executeQuery();

        totalCarriage = calcCarriage(month,year,clientName);

        //tabular form
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s %20s", "Item", "Price", "Credit", "Total");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");

        while (resultMonth.next()){
            itemName = resultMonth.getString(2);
            credit = resultMonth.getDouble(3);
            itemPrice = resultMonth.getDouble(4);
            manualDate = resultMonth.getString(6);

            itemTotal = credit*itemPrice;
   
            list.add(new invoices(itemName, itemPrice, credit, itemTotal));

            System.out.format("%10s %30s %20s %20s", itemTotal, credit, itemPrice, itemName);
            System.out.println();

            noTaxTotal = noTaxTotal + itemTotal;
            deleteCalculated(clientName,itemName);
        }

        String selectHalf = "SELECT * FROM الفاتورة WHERE clientName=? AND DAY(manualDate)>14 AND MONTH(manualDate)=? AND YEAR(manualDate)=?";
        PreparedStatement statement2 = con.prepareStatement(selectHalf);
        statement2.setString(1,clientName);
        statement2.setString(2,month);
        statement2.setString(3,year);

        ResultSet resultHalf = statement2.executeQuery();


        while (resultHalf.next()){
            itemName = resultHalf.getString(2);
            credit = resultHalf.getDouble(3);
            itemPrice = resultHalf.getDouble(4);
            manualDate = resultHalf.getString(6);
            itemTotal = credit*0.5*itemPrice;
         
            list.add(new invoices(itemName, itemPrice, credit, itemTotal));

            System.out.format("%10s %30s %20s %20s", itemTotal, credit, 0.5*itemPrice, itemName);
            System.out.println();

            noTaxTotal = noTaxTotal + itemTotal;
            deleteCalculated(clientName,itemName);
        }

        reserved = calcReserved(clientName,month,year);
        noTaxTotal = noTaxTotal + reserved + totalCarriage;
        total = noTaxTotal + (noTaxTotal*0.14);
        Tcarriage = totalCarriage;
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Total Payment is "+noTaxTotal);
        System.out.println("Total Payment with Taxes is "+total+"\n");
        
        

      return list;
    }


}


