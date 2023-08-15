
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
import systemconsole.Clients;

public class ClientsFXMLController implements Initializable {

    Clients client = new Clients();
     
    @FXML
    public TextField clientName;
    @FXML
    public JFXButton deletebtn;
    @FXML
    public JFXButton addbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void deletebtnAction(ActionEvent event) throws Exception {
         Window owner = deletebtn.getScene().getWindow();

        if(event.getSource() == deletebtn){
             if (clientName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالعملية!",
                "برجاء ادخال الاسم العميل");
            return;
        }
            client.deleteClient(clientName.getText());
             showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تم مسح العميل  بنجاح!",
            "تم مسح العميل " + clientName.getText());     
             
            clientName.clear();
            
        }else{
                          showAlert(Alert.AlertType.ERROR, owner, "خطأ!",
                "لم يتم مسح العميل بنجاح برجاء التأكد من ان العميل  له ملف فى قاعدة البيانات");
            return;
        }
        
    }

    @FXML
    public void addbtnAction(ActionEvent event) throws Exception {
         Window owner = addbtn.getScene().getWindow();
        
        if(event.getSource() == addbtn){
             if (clientName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالعملية!",
                "برجاء ادخال الاسم العميل");
            return;
        }
         client.newClient(clientName.getText()); 
         showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تم اضافة العميل  بنجاح!",
            "تم اضافة العميل " + clientName.getText());         
            
          clientName.clear();
            
        }else{
              showAlert(Alert.AlertType.ERROR, owner, "خطأ!",
                "لم يتم اضافة العميل بنجاح برجاء التأكد من ان العميل ليس له ملف فى قاعدة البيانات");
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
    
}
