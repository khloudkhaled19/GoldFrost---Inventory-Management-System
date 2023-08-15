
package systemconsole;

import java.sql.Timestamp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
//import static javafx.beans.property.DoubleProperty.doubleProperty;
import javafx.beans.property.StringProperty;


public class report {
    
    
    private  StringProperty clientName, item, manualDate,Code;
    private  DoubleProperty   price, credit,incomeoutcome;

    
     public report(String Cname, String Iname, String date, double Iprice, double Icredit) {
      this.clientName = new SimpleStringProperty(Cname);
      this.item = new SimpleStringProperty(Iname);
      this.manualDate = new SimpleStringProperty(date);
      this.price = new SimpleDoubleProperty(Iprice);
      this.credit = new SimpleDoubleProperty(Icredit);

    }
    
     public report(String Cname, String Iname, double Io,String inCode, double Iprice, double Icredit,String date){
      this.clientName = new SimpleStringProperty(Cname);
      this.item = new SimpleStringProperty(Iname);
      this.incomeoutcome = new SimpleDoubleProperty(Io);
      this.Code= new SimpleStringProperty(inCode);
      this.manualDate = new SimpleStringProperty(date);
      this.price = new SimpleDoubleProperty(Iprice);
      this.credit = new SimpleDoubleProperty(Icredit);
     }
     

    public  String getClientName() { return clientName.get(); }
    public  void setClientName(String Cname) {this.clientName.set(Cname);}
    public StringProperty clientNameProperty() { return clientName; } 
    public  String getItem() { return item.get();}
    public  void setItem(String Iname) { this.item.set(Iname);}
    public StringProperty itemProperty() { return item; } 
    public  double getIncomeoutcome() { return incomeoutcome.get();}
    public  void setIncomeoutcome(double Io) { this.incomeoutcome.set(Io); }
    public DoubleProperty incomeoutcomeProperty() { return incomeoutcome; }
    public  String getCode() { return Code.get(); }
    public  void setCode(String inCode) {this.Code.set(inCode);}
    public StringProperty codeProperty() { return Code; }
    public  double getPrice() { return price.get();}
    public  void setPrice(double Iprice) { this.price.set(Iprice); }
    public DoubleProperty priceProperty() { return price; } 
    public double getCredit() {   return credit.get();}
    public  void setCredit(double Icredit) {this.credit.set(Icredit);}
    public DoubleProperty creditProperty() { return credit; } 
    public String getManualDate() {return manualDate.get(); }
    public  void setManualDate(String date) {this.manualDate.set(date);}
    public StringProperty manualDateProperty() { return manualDate; } 

}
