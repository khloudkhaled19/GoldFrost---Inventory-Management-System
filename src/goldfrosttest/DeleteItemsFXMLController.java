
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

public class DeleteItemsFXMLController implements Initializable {
    
    items item = new items();

    @FXML
    private TextField itemNameField;
    @FXML
    private TextField clientNameField;
    @FXML
    private JFXButton Delbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Delbtn(ActionEvent event) throws Exception {
         Window owner = Delbtn.getScene().getWindow();
        if(event.getSource()== Delbtn){
             if (clientNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالعملية!",
                "برجاء ادخال الاسم العميل");
            return;
        }
        if (itemNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالعملية!",
                "برجاء ادخال الاسم البضاعة");
            return;
        }     
            item.deleteItems(clientNameField.getText() ,itemNameField.getText());
            showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تم مسح البضاعة  بنجاح!",
            "تم مسح البضاعة " + itemNameField.getText() + "الخاص بالعميل " +" "+ clientNameField.getText() ); 
            
            clientNameField.clear();
            itemNameField.clear();
        }else{
                showAlert(Alert.AlertType.ERROR, owner, "خطأ!",
                "لم يتم مسح البضاعة بنجاح برجاء التأكد من ان العميل  له ملف فى قاعدة البيانات و ان لديه هذه البضاعة");
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
    
}
