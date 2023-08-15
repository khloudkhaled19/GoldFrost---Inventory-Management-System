
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import systemconsole.report;
import systemconsole.Reports;
public class AdminIncomesFXMLController implements Initializable {


    
    @FXML
    private JFXButton showbtn;

    @FXML
    private TextField clientField;

    @FXML
    private TableView<report> table;
    @FXML
    private TableColumn<report, String> clientName;
    @FXML
    private TableColumn<report, String> itemName;
    @FXML
    private TableColumn<report, Double> income;
    @FXML
    private TableColumn<report, String> incomecode;
    @FXML
    private TableColumn<report, Double> credit;
    @FXML
    private TableColumn<report, Double> price;
    @FXML
    private TableColumn<report, String> date;
    

    
    
    ObservableList<report> listM ;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         clientName.setCellValueFactory(new PropertyValueFactory<report, String>("clientName"));
         itemName.setCellValueFactory(new PropertyValueFactory<report, String>("item"));
         income.setCellValueFactory(new PropertyValueFactory<report, Double>("incomeoutcome"));
         incomecode.setCellValueFactory(new PropertyValueFactory<report, String>("Code"));
         credit.setCellValueFactory(new PropertyValueFactory<report, Double>("credit"));
         price.setCellValueFactory(new PropertyValueFactory<report, Double>("price"));
         date.setCellValueFactory(new PropertyValueFactory<report, String>("manualDate"));

     
    }    


    @FXML
    void showbtnAction(ActionEvent event) {
       if(event.getSource() == showbtn ){
           String Cname = clientField.getText();
          Cname = Cname.replaceAll(" ", "_").toLowerCase();
          
          listM = Reports.incomeReports(Cname);
          table.setItems(listM);
       }
    }
    
}
