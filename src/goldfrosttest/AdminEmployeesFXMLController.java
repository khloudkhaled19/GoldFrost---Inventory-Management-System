
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import systemconsole.Employees;
import systemconsole.employee;

public class AdminEmployeesFXMLController implements Initializable {

    @FXML
    private TableView<employee> employeeTable;
    @FXML
    private TableColumn<employee, String> Ename;
    @FXML
    private TableColumn<employee, String> Ephone;
    @FXML
    private TableColumn<employee, String> Eemail;
    @FXML
    private TableColumn<employee, String> Ejobtitle;
    @FXML
    private JFXButton deletebtn;
        @FXML
    private TextField emailField;
        

    ObservableList<employee> listM ;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Ename.setCellValueFactory(new PropertyValueFactory<employee, String>("fullName"));   
            Ephone.setCellValueFactory(new PropertyValueFactory<employee, String>("phone"));   
            Eemail.setCellValueFactory(new PropertyValueFactory<employee, String>("email"));   
            Ejobtitle.setCellValueFactory(new PropertyValueFactory<employee, String>("jobTitle"));  
            listM = Employees.showEmployees();
            employeeTable.setItems(listM);
            
            
 
    }    


    @FXML
    private void deletebtnAction(ActionEvent event) throws SQLException {
        Window owner = deletebtn.getScene().getWindow();

        if(event.getSource() == deletebtn){
           
        if (emailField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ فى ادخال البيانات!",
                "برجاء ادخال  البريد الالكترونى بشكل صحيح");
            return;
        }
            employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
           // String mail = selectedEmployee.getEmail().toString();
            Employees.deleteEmployee(emailField.getText());
            employeeTable.getItems().removeAll(employeeTable.getSelectionModel().getSelectedItem());
            showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تمت العملية بنجاح!",
            "لقد تم مسح الموظف من قاعدة البيانات");            
            
            emailField.clear();
        }
        else{
                  showAlert(Alert.AlertType.ERROR, owner, "خطأ!",
                "لم يتم مسح الموظف تأكد بأن البريد الالكترونى الخاص بالموظف صحيح!");
            return;
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
