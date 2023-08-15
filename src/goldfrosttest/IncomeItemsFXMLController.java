
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import systemconsole.items;

public class IncomeItemsFXMLController implements Initializable {

    @FXML
    private TextField itemNameField;
    @FXML
    private TextField clientNameField;
    @FXML
    private TextField outcomeField;
    @FXML
    private TextField incomeField;
    @FXML
    private TextField codeField;
    @FXML
    private JFXButton incomeSubmitbtn;
    
    @FXML
    private TextField yearField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField dayField;
    @FXML
    private TextField carriage;


    items item = new items();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void incomeSubmitbtnAction(ActionEvent event) throws Exception {
       Window owner = incomeSubmitbtn.getScene().getWindow();

        if(event.getSource()== incomeSubmitbtn){
           
        if (clientNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال اسم العميل");
            return;
        }

        if (itemNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال اسم البضاعة");
            return;
        }
        if (incomeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال الوارد");
            return;
        }
        if (outcomeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال الصادر بصفر كرقم");
            return;
        }
        if (codeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء ادخال اذن الوارد");
            return;
        }
        if (dayField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء تأكيد اليوم كرقم");
            return;
        }
        if (monthField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء ادخال الشهر كرقم");
            return;
        }
        if (yearField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء ادخال السنة كرقم");
            return;
        } 
        if (carriage.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء ادخال التداول");
            return;
        }         
        
            String Cname = clientNameField.getText();
            String Iname = itemNameField.getText();
            double income = Double.valueOf(incomeField.getText());
            double outcome = Double.valueOf(outcomeField.getText());
            String code = codeField.getText();
            String day = dayField.getText();
            String month = monthField.getText();
            String year = yearField.getText();
            double Carriage = Double.valueOf(carriage.getText());

            item.incomeOutcome(Cname, Iname, income, outcome, code, day, month, year, Carriage);
            showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تم اضافة الوارد  بنجاح!",
            "تم اضافة الوارد للعميل  " + clientNameField.getText()); 
            
            clientNameField.clear();
            itemNameField.clear();
            incomeField.clear();
            outcomeField.clear();
            codeField.clear();
            dayField.clear();
            monthField.clear();
            yearField.clear();
            carriage.clear();
            
        }else{
                 showAlert(Alert.AlertType.ERROR, owner, "خطأ!",
                "لم يتم اضافة الوارد بنجاح برجاء التأكد من ان العميل  له ملف فى قاعدة البيانات");
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
