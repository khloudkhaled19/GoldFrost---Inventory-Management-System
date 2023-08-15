
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import systemconsole.Reports;

import systemconsole.report;


public class MonthlyReportsFXMLController implements Initializable {
    
    @FXML
    private JFXButton showbtn;

    @FXML
    private TextField yearField;

    @FXML
    private TextField monthField;

    @FXML
    private TableView<report> table;
    
     @FXML
    private TableColumn<report, String> clientName;

    @FXML
    private TableColumn<report, String> itemName;

    @FXML
    private TableColumn<report, String> date;

    @FXML
    private TableColumn<report, Double> credit;

    @FXML
    private TableColumn<report, Double> price;
    
       ObservableList<report> listM ;

    @FXML
    void showbtnAction(ActionEvent event) {
         Window owner = showbtn.getScene().getWindow();
        
          if(event.getSource() == showbtn){
  
            String month = monthField.getText();
            String year = yearField.getText();
            
            if (monthField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالعملية!",
                "برجاء ادخال الشهر كرقم");
            return;
        }
            if (yearField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالعملية!",
                "برجاء ادخال السنة كرقم");
            return;
        }            
            
            listM = Reports.MonthlyReports(month, year);
            table.setItems(listM); 
            
            monthField.clear();
            yearField.clear();
       }else{
               showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء التأكد من ان التاريخ صحيح ");
            return;
          }
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         clientName.setCellValueFactory(new PropertyValueFactory<report, String>("clientName"));
         itemName.setCellValueFactory(new PropertyValueFactory<report, String>("item"));
         date.setCellValueFactory(new PropertyValueFactory<report, String>("manualDate"));
         credit.setCellValueFactory(new PropertyValueFactory<report, Double>("credit"));
         price.setCellValueFactory(new PropertyValueFactory<report, Double>("price"));
         
           
    }    
    
    
}
