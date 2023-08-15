
package systemconsole;


import java.sql.*;
import java.util.*;
import java.io.ObjectInputStream.GetField;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class SystemConsole {

    public static void main(String[] args) throws Exception {

        items item = new items();
        Clients client = new Clients();
        Inventory inventory = new Inventory();
        Invoice invoice = new Invoice();
        SignUP signup = new SignUP();

        String tableName, itemName, incomeCode, outcomeCode,month,year, firstName, lastName, email, gender, Password, confpassword, phone, jobTitle;
        double  itemPrice, income, outcome, credit;
        Timestamp date;
        String clientName, clientItem;
        int choice;
        System.out.println(Charset.defaultCharset().name());
        Scanner sc = new Scanner(System.in, "UTF-8" );
        invoice.createInvoiceTable();
        inventory.createInventoryTable();
        inventory.createIncomeOutcomeCodesTable();
        signup.createEmployeesTable();
        

        //System.out.println(item.getInputDate());
       // System.out.println(item.getCurrentTime());



        //try {
            do {
                System.out.println("1-ادخال عميل جديد\n2-ادخال بضاعة جديدة للعميل\n3-وارد جديد\n4-صادر جديد\n5-مسح للبضائع\n6-مسح للعميل\n7-اظهار الموظفين\n8-التقارير \n9-حساب الفاتورة\n10-تسجيل دخول\n11-خروج\n");
                choice = sc.nextInt();

                switch (choice) {
                    /**
                    case 0:
                        System.out.println("Enter the client list name");
                        tableName = sc.next();
                      //  client.CreateClientsList(tableName);
                        System.out.println("the list is created successfully ");
                        break;
                    **/
                    
                    case 0:
                        System.out.println("سجل بياناتك :");
                        
                        System.out.println("ادخل اسمك الاول:");
                        firstName = sc.next();
                        System.out.println("ادخل اسمك الثانى:");
                        lastName = sc.next();
                        System.out.println("ادخل رقم تليفونك:");
                        phone = sc.next();
                        System.out.println("ادخل ايميلك:");
                        email = sc.next();
                        System.out.println("ادخل منصبك:");
                        jobTitle = sc.next();
      
                        System.out.println("ادخل رقمك السرى:");
                        Password = sc.next();
                        System.out.println("ادخل الرقم السرى للتأكيد:");
                        confpassword = sc.next();
                        
                        signup.Registration(firstName, lastName, phone, email, jobTitle, Password, confpassword);
                        
                        
                        break;
                    case 1:
                        System.out.println("اسم العميل :");
                       // String List = "clientslist";
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        client.newClient(tableName);

                        //client.addToClientsList(tableName,List);

                     //   client.addToClientsList(tableName,"clientsList");
                        break;

                    case 2:
                        System.out.println(": اسم العميل  ");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        System.out.println(": اسم البضاعة ");
                       // sc.nextLine();
                        itemName = sc.nextLine();
                      //  System.out.println("Weight: ");
                      //  itemWeight = sc.nextDouble();
                        System.out.println(": الوارد");
                         income = sc.nextDouble();
                      
                        System.out.println(":كود المدخلات");
                        incomeCode = sc.next();

                        outcome = 0;
                      //  System.out.println("outcome code:");
                     //   outcomeCode = sc.next();

                        System.out.println(":السعر ");
                        itemPrice = sc.nextDouble();
                        credit = income;

                       // item.addNewItem(tableName, itemName,income,incomeCode, outcome,itemPrice, credit);
                        break;

                    case 3:
                        System.out.println(": اسم العميل   ");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        System.out.println(":اسم البضاعة ");
                      //  sc.nextLine();
                        itemName = sc.nextLine();
                        System.out.println(":كمية الوارد ");
                        income = sc.nextDouble();
                        outcome = 0;
                        
                        System.out.println("ادخل اذن الوارد");
                        incomeCode = sc.next();

                     //   item.incomeOutcome(tableName, itemName, income, outcome, incomeCode);
                        break;

                    case 4:
                        System.out.println(":اسم العميل ");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        System.out.println(":اسم البضاعة ");
                       // sc.nextLine();
                        itemName = sc.nextLine();
                        System.out.println(" : كمية الصادر");
                        outcome = sc.nextDouble();
                        income = 0;
                        
                        System.out.println("ادخل اذن الصادر");
                        outcomeCode = sc.next();

                       // item.incomeOutcome(tableName, itemName, income, outcome, outcomeCode);
                        break;

                    case 5:
                        System.out.println(" : اسم العميل");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        System.out.println(" : اسم البضاعة");
                       // sc.nextLine();
                        itemName = sc.nextLine();

                        item.deleteItems(tableName, itemName);
                        break;

                    case 6:
                        System.out.println(": ادخل اسم العميل الذى تريد مسحه ");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        client.deleteClient(tableName);
                        break;

                    case 7:
                        //System.out.println("You are now off");
                        System.out.println("من فضلك ادخل اسم العميل الذى تريد اظهار بياناته");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        System.out.println("بيانات الموظفين");
                        inventory.ShowclientInventory(tableName);

                        break;

                    case 8:
                        // System.out.println("The Clients List");

                        // inventory.ShowClientsList();
                        //     inventory.ShowAllInventory();
                        //    System.out.println("Enter the day you want to show in YYYY-MM-DD");
                        Reports report = new Reports();

                        //      String dateString - sc.next();
                        System.out.println("Enter the month in the format dd");
                        String  dateMonth = sc.next();
                        System.out.println("Enter the year");
                        String  dateYear = sc.next();

                        //  dateString = format.format( new Date());
                        // Date date2 = format.parse(dateString);

                        report.MonthlyReports(dateMonth, dateYear);
                        // report.dailyReports(dateString);
                        //     report.YearlyReports(dateString);

                        break;

                    case 9:
                        System.out.println("ادخل اسم العميل: ");
                        sc.nextLine();
                        tableName = sc.nextLine().replaceAll("\\s+", "_").toLowerCase();
                        System.out.println("ادخل الشهر كرقم: ");
                        month = sc.next();
                        System.out.println("ادخل السنة كرقم: ");
                        year = sc.next();
                       invoice.calcInvoice(tableName, month, year);
                        break;
                        
                    case 10:  
                        System.out.println("تسجيل الدخول");
                        
                        System.out.println("ادخل ايميلك");
                        email = sc.next();
                        System.out.println("ادخل رقمك السرى");
                        Password = sc.next();
                        
                        signup.Login(email, Password);
                        break;
                    case 11:
                        System.out.println("you are now off");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }

            } while (choice != 11);
        //}catch (Exception e) {
          //  System.out.println("System Error Occured, Try Again");
        //}
    }
}

