package systemconsole;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.ObjectInputStream.GetField;
import java.awt.*;
import java.io.*;
import java.util.Date;
import java.text.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import javafx.scene.control.Alert;
import goldfrosttest.ClientsFXMLController;
import javax.swing.JOptionPane;



public class items {

    public static String tableName,itemName, incomeCode, outcomeCode, code;
    public static double itemPrice, income,outcome,credit;
    public static Timestamp date,manualDate;
    public static String username = "root";
    public static String password = "abc-123";
    public static String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf8";
    public static String driver = "com.mysql.jdbc.Driver";


    public items() {
        // TODO Auto-generated constructor stub
    }

    public static void createItemTable(String tableName) throws Exception //creating item table with the client name
    {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);
         DatabaseMetaData dbm = con.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "الفاتورة", null);

        if (tables.next()) {
            //if table exists do nothing
           JOptionPane.showMessageDialog(null , "العميل موجود مسبقا فى قاعدة البيانات");

        }
        else {
            // Table does not exist
        tableName = tableName.toLowerCase();
        tableName = tableName.replaceAll("\\s","");
        String sqlStatement="CREATE TABLE "+tableName+" ("
                + "item VARCHAR(1000) NOT NULL,"
                + "income DOUBLE,"
                + "incomeCode VARCHAR(1000),"
                + "outcome DOUBLE,"
                + "outcomeCode VARCHAR(1000),"
                + "date TIMESTAMP,"
                + "manualDate TIMESTAMP,"
                + "credit DOUBLE,"
                + "price DOUBLE,"
                +"carriage DOUBLE"
                + " )";


        PreparedStatement s = con.prepareStatement(sqlStatement);
        s.executeUpdate();
        System.out.println("Table created");

        }


    }
    
         private static void showAlert(Alert.AlertType alertType, javafx.stage.Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    

    public static Timestamp getCurrentDate() throws Exception
    {

        LocalDateTime now = LocalDateTime.now();
        Timestamp sqlNow = Timestamp.valueOf(now);

        return sqlNow;

    }
/*
    public static Timestamp getCurrentTime()throws Exception
    {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date(ts.getTime());
        ts = new Timestamp(date.getTime());


        /*
        * Date date = new Date();
                Timestamp ts=new Timestamp(date.getTime());
        * */



    public static Timestamp getManualDate(String day, String month, String year)throws Exception
    {
      //  String day,month,year,fullDate;
        String fullDate;

        fullDate = year+"-"+month+"-"+day;

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter1.parse(fullDate);
        Timestamp ts = new Timestamp(date.getTime());

        return ts;

    }

    public static double calCredit(double credit,double income, double outcome) throws Exception
    {
        credit = credit + income - outcome;

        return credit;
    }

    //You need to edit insertStatement values :))))
    //need to calc indate and credit before insert statement
    public static void addNewItem(String tableName, String itemName,double income, String incomeCode, double outcome,double itemPrice,double credit, String day, String month, String year, double carriage) throws Exception{

        //open connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);


        //calc indate
        date = getCurrentDate();
        manualDate = getManualDate(day, month, year);
        //credit = calCredit(credit,income,outcome);

        //normally inserting new values
        String selectAll = "SELECT * FROM"+" "+tableName;
 
        Statement insertAll = con.createStatement();
        insertAll.addBatch(

                "INSERT INTO"+" "+ tableName+"(`item`,`income`, `incomeCode`, `outcome`, `date`, `manualDate`, `credit`, `price`,`carriage`) "
                        +"VALUES ('"+itemName+"','"+income+"','"+incomeCode+"','"+outcome+"','"+date+"','"+manualDate+"','"+credit+"', '"+itemPrice+"', '"+carriage+"')"
        );

        insertAll.addBatch(
                "INSERT INTO المخازن (`clientName`,`item`,`date`,`manualDate` ,`credit`, `price`)"
                        + "VALUES ('"+tableName+"','"+itemName+"','"+date+"','"+manualDate+"','"+credit+"', '"+itemPrice+"')"

        );

        insertAll.addBatch(
                "INSERT INTO الفاتورة (`clientName`,`item`, `credit`, `price`, `date`, `manualDate`,`carriage`)"
                        + "VALUES ('"+tableName+"','"+itemName+"','"+credit+"','"+itemPrice+"','"+date+"','"+manualDate+"', '"+carriage+"')"

        );
        insertAll.addBatch(
             
            "INSERT INTO الاذون (`clientName`,`item`,`income`,`incomeCode`, `outcome`,`date`, `manualDate`, `credit`, `price`)"
                        + "VALUES ('"+tableName+"','"+itemName+"','"+income+"','"+incomeCode+"','"+outcome+"','"+date+"','"+manualDate+"','"+credit+"', '"+itemPrice+"')"
        );

        PreparedStatement preSelect = con.prepareStatement(selectAll);
        //PreparedStatement preInsert = con.prepareStatement(insertAll) ;
        ResultSet selectResult = preSelect.executeQuery();


        int[] results = insertAll.executeBatch();
        System.out.println("Successfully added ^_^");
    }


    public static void deleteItems(String tableName,String itemName) throws Exception{
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

          int [] deleteResult = deleteAll.executeBatch();

            System.out.println("Successfully deleted");
        }else{
            System.out.println("No item has this name, try again");
        }

    }

    //if income let outcome = 0 and vice versa
    public static void incomeOutcome(String tableName,String itemName,double income,double outcome, String code, String day, String month, String year, double carriage) throws Exception
    {

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,username,password);

        String selectAll = "SELECT * FROM "+tableName+" WHERE item = '" + itemName + "' ORDER BY date DESC LIMIT 1";
        PreparedStatement statement = con.prepareStatement(selectAll);
       // statement.setString(1, itemName);
        ResultSet result = statement.executeQuery();

        //check if item exist, not working i no idea why
        boolean flag = false;
        while(result.next()){
            if(itemName.equalsIgnoreCase(result.getString(1))){
                flag = true;
                System.out.println("the item exists");
                break;
            }
            else{
                flag = false;
                System.out.println("the item doesn't exists");
            }
        }

        if(flag)
        {  
            
          
            credit = result.getDouble(8); //9
            credit = calCredit(credit, income, outcome);
            System.out.println("credit is = "+credit);
            itemPrice = result.getDouble(9);  //10
            
            String upDelete = "DELETE FROM الفاتورة WHERE clientName=? AND item=?";
            PreparedStatement statement2 = con.prepareStatement(upDelete);
            statement2.setString(1,tableName); //1
            statement2.setString(2,itemName); //2
            statement2.executeUpdate();
             

            if(income == 0 ){
                
        date = getCurrentDate();
        manualDate = getManualDate(day, month, year);
                
            Statement out = con.createStatement();
        out.addBatch(

                "INSERT INTO"+" "+ tableName+"(`item`,`income`, `outcome`, `outcomeCode`, `date`, `manualDate`, `credit`, `price`, `carriage`) "
                        +"VALUES ('"+itemName+"','"+income+"','"+outcome+"','"+code+"','"+date+"','"+manualDate+"','"+credit+"', '"+itemPrice+"', '"+carriage+"')"
        );

        out.addBatch(
                "INSERT INTO المخازن (`clientName`,`item`,`date`,`manualDate` ,`credit`, `price`)"
                        + "VALUES ('"+tableName+"','"+itemName+"','"+date+"','"+manualDate+"','"+credit+"', '"+itemPrice+"')"

        );

        out.addBatch(
                "INSERT INTO الفاتورة (`clientName`,`item`, `credit`, `price`, `date`, `manualDate`, `carriage`)"
                        + "VALUES ('"+tableName+"','"+itemName+"','"+credit+"','"+itemPrice+"','"+date+"','"+manualDate+"','"+carriage+"')"

        );
        out.addBatch(
             
            "INSERT INTO الاذون (`clientName`,`item`,`income`,`outcome`, `outcomeCode`,`date`, `manualDate`, `credit`, `price`)"
                        + "VALUES ('"+tableName+"','"+itemName+"','"+income+"','"+outcome+"','"+code+"','"+date+"','"+manualDate+"','"+credit+"', '"+itemPrice+"')"
        );
        
         int[] outresults = out.executeBatch();
             
            }
            else if(outcome == 0){
            
              addNewItem(tableName,itemName,income, code, outcome,itemPrice,credit, day, month, year, carriage);

            }


        }else{
            System.out.println("No item has this name, try again");
        }
    }





/*
    public static void updateItems(String itemName)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/system", "root", "12345678");

        PreparedStatement statement = con.prepareStatement("SELECT * FROM items");
        ResultSet result = statement.executeQuery();
        int choice;
        String newName;
        double newWeight,newQuantity,newPrice;
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
        if(flag){
            System.out.println("1-Update Name\t2-Update Weight\t3-Update Quantity\t4-Update Price");
            Scanner sc = new Scanner(System.in);
            choice=sc.nextInt();
            switch (choice){
                case 1:

                    System.out.println("Enter new Name: ");
                    newName = sc.next();
                    PreparedStatement name = con.prepareStatement("update items set item=? where item=?");
                    name.setString(1,newName);
                    name.setString(2,itemName);
                    name.executeUpdate();
                    break;
                case 2:

                    System.out.println("Enter new Weight: ");
                    newWeight = sc.nextDouble();
                    PreparedStatement weight = con.prepareStatement("update items set weight=? where item=?");
                    weight.setDouble(1,newWeight);
                    weight.setString(2,itemName);
                    weight.executeUpdate();
                    break;
                case 3:

                    System.out.println("Enter new quantity: ");
                    newQuantity = sc.nextDouble();
                    PreparedStatement quantity = con.prepareStatement("update items set quantity=? where item=?");
                    quantity.setDouble(1,newQuantity);
                    quantity.setString(2,itemName);
                    quantity.executeUpdate();

                    break;
                case 4:

                    System.out.println("Enter new price: ");
                    newPrice = sc.nextDouble();
                    PreparedStatement price = con.prepareStatement("update items set price=? where item=?");
                    price.setDouble(1,newPrice);
                    price.setString(2,itemName);
                    price.executeUpdate();
                    break;

            }
            System.out.println("Successfully Updated");
        }

    }*/



}