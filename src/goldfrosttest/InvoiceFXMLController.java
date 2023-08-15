
package goldfrosttest;

//import static GUI.LoginFXMLController.user;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import systemconsole.Invoice;
import systemconsole.invoices;
import systemconsole.report;

public class InvoiceFXMLController implements Initializable {

    @FXML
    private TextField CnameField;
    @FXML
    private TextField monthField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField orderDateField;
 
    @FXML
    private JFXButton enterbtn;
    @FXML
    private TableView<invoices> table;
    @FXML
    private TableColumn<invoices, String> itemName;
    @FXML
    private TableColumn<invoices, Double> credit;
    @FXML
    private TableColumn<invoices, Double> price;
      @FXML
    private TableColumn<invoices, Double> total;

   @FXML
    private Label totalNoTaxes;

    @FXML
    private Label totalTaxes;
    
    @FXML
    private Label jtotalCarriage;
 
    ObservableList<invoices> listM ;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         itemName.setCellValueFactory(new PropertyValueFactory<invoices, String>("itemName"));
         credit.setCellValueFactory(new PropertyValueFactory<invoices, Double>("credit"));
         price.setCellValueFactory(new PropertyValueFactory<invoices, Double>("itemPrice"));
         total.setCellValueFactory(new PropertyValueFactory<invoices, Double>("itemTotal"));


    }    

 

    @FXML
    private void enterbtnAction(ActionEvent event) throws Exception {
          if(event.getSource() == enterbtn){
            String client = CnameField.getText();
            String month = monthField.getText();
            String year = yearField.getText();
            
             Window owner = enterbtn.getScene().getWindow();
           
        if (CnameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى ادخال البيانات!",
                "برجاء ادخال اسم العميل بشكل صحيح !");
            return;
        }
        if (monthField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى ادخال البيانات!",
                "برجاء ادخال الشهر كرقم");
            return; 
        }
        if (yearField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى ادخال البيانات!",
                "برجاء ادخال السنة كرقم");
            return; 
        }
           // String carriageValue = carriage.getText();
           listM = Invoice.calcInvoice(client,month, year);
            table.setItems(listM); 
            
            totalTaxes.setText(Double.toString(Invoice.noTaxTotal));
            totalNoTaxes.setText(Double.toString(Invoice.total));
            jtotalCarriage.setText(Double.toString(Invoice.Tcarriage));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            orderDateField.setText(dateFormat.format(date));


            
       }

    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    
}
