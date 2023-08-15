
package systemconsole;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
//import static javafx.beans.property.DoubleProperty.doubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
public class invoices {

     StringProperty itemName, incomeCode, outcomeCode,month,year, manualDate;
     DoubleProperty clientName,itemPrice, income,outcome,credit,itemTotal,noTaxTotal, total, carriage;
   // public static TextField carriage;
    
      public invoices(String itemName, Double itemPrice, Double credit, Double itemTotal) {
        this.itemName = new SimpleStringProperty(itemName) ;
      //  this.carriage = new SimpleDoubleProperty(carriage);
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
        this.credit = new SimpleDoubleProperty(credit);
        this.itemTotal = new SimpleDoubleProperty(itemTotal);
 }
      /**
      public invoices(String itemName, Double itemPrice, Double credit, Double itemTotal ,Double noTaxTotal) {
        this.itemName = new SimpleStringProperty(itemName) ;
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
        this.credit = new SimpleDoubleProperty(credit);
        this.itemTotal = new SimpleDoubleProperty(itemTotal);
        this.noTaxTotal = new SimpleDoubleProperty(noTaxTotal);
 }
 * */
    public  String getItemName() {
        return itemName.get();
    }

    public  void setItemName(String itemN) {
        itemName.set(itemN);
    }
    public StringProperty itemNameProperty() { return itemName; } 
    

    public  String getManualDate() {
        return manualDate.get();
    }

    public  void setManualDate(String date) {
        manualDate.set(date);
    }

    public StringProperty manualDateProperty() { return manualDate; } 

    public  Double getItemPrice() {
        return itemPrice.get();
    }

    public  void setItemPrice(Double price) {
        itemPrice.set(price);
    }

    public DoubleProperty itemPriceProperty() { return itemPrice; } 
 

    public  Double getCredit() {
        return credit.get();
    }

    public  void setCredit(Double itemCredit) {
        credit.set(itemCredit);
    }
    public DoubleProperty creditProperty() { return credit; } 

    public  Double getItemTotal() {
        return itemTotal.get();
    }

    public  void setItemTotal(Double Itotal) {
        itemTotal.set(Itotal);
    }
    public DoubleProperty itemTotalProperty() { return itemTotal; } 

    public  Double getNoTaxTotal() {
        return noTaxTotal.get();
    }

    public  void setNoTaxTotal(Double noTtotal) {
       noTaxTotal.set(noTtotal);
    }
  public DoubleProperty noTaxTotaltProperty() { return noTaxTotal; } 


    public  Double getTotal() {
        return total.get();
    }

    public  void setTotal(Double Total) {
       total.set(Total);
    }
  public DoubleProperty totaltProperty() { return total; } 
  
      public  Double getCarriage() {
        return carriage.get();
    }

    public  DoubleProperty carriageProperty() {
        return carriage;
    }

    public  void setCarriage(Double Carriage) {
        carriage.set(Carriage);
    }
  
    
    
}
