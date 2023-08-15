
package systemconsole;

import java.sql.Timestamp;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
//import static javafx.beans.property.DoubleProperty.doubleProperty;
import javafx.beans.property.StringProperty;

public class inventories {
    
    private  StringProperty clientName, item, manualDate,incomeCode, outcomecode;
    private  DoubleProperty   price, credit,income, outcome;

    public inventories(String clientName, String item, String manualDate, String incomeCode, String outcomecode, Double price, Double credit, Double income, Double outcome) {
        this.clientName = new SimpleStringProperty(clientName) ;
        this.item = new SimpleStringProperty(item);
        this.manualDate = new SimpleStringProperty(manualDate);
        this.incomeCode = new SimpleStringProperty(incomeCode);
        this.outcomecode = new SimpleStringProperty(outcomecode);
        this.price = new SimpleDoubleProperty(price);
        this.credit = new SimpleDoubleProperty(credit);
        this.income = new SimpleDoubleProperty(income);
        this.outcome = new SimpleDoubleProperty(outcome) ;
    }
    
    public inventories(String clientName, String item, String manualDate, String incomeCode, Double price, Double credit, Double income, Double outcome) {
        this.clientName = new SimpleStringProperty(clientName) ;
        this.item = new SimpleStringProperty(item);
        this.manualDate = new SimpleStringProperty(manualDate);
        this.incomeCode = new SimpleStringProperty(incomeCode);
        this.price = new SimpleDoubleProperty(price);
        this.credit = new SimpleDoubleProperty(credit);
        this.income = new SimpleDoubleProperty(income);
        this.outcome = new SimpleDoubleProperty(outcome) ;

    }    
    
    public  String getClientName() { return clientName.get(); }
    public  void setClientName(String clientName) {this.clientName.set(clientName);}
    public StringProperty clientNameProperty() { return clientName; } 
     public  String getItem() { return item.get();}
    public  void setItem(String item) { this.item.set(item);}
    public StringProperty itemProperty() { return item; } 
    public  String getIncomeCode() { return incomeCode.get();}
    public  void setIncomeCode(String incomeCode) { this.incomeCode.set(incomeCode); }
    public StringProperty outcomecodeProperty() { return outcomecode; }
    public  String getOutcomeCode() { return outcomecode.get(); }
    public  void setOutcomeCode(String outcomecode) {this.outcomecode.set(outcomecode);}
    public StringProperty outcomeCodeProperty() { return outcomecode; }
    public  double getPrice() { return price.get();}
    public  void setPrice(double Iprice) { this.price.set(Iprice); }
    public DoubleProperty priceProperty() { return price; } 
    public double getCredit() {   return credit.get();}
    public  void setCredit(double Icredit) {this.credit.set(Icredit);}
    public DoubleProperty creditProperty() { return credit; } 
    public String getManualDate() {return manualDate.get(); }
    public  void setManualDate(String date) {this.manualDate.set(date);}
    public StringProperty manualDateProperty() { return manualDate; } 
    public double getIncome() {   return income.get();}
    public  void setIncome(double income) {this.income.set(income);}
    public DoubleProperty incomeProperty() { return income; }
    public double getOutcome() {   return outcome.get();}
    public  void setOutcome(double outcome) {this.outcome.set(outcome);}
    public DoubleProperty outcomeProperty() { return outcome; }
  
}
