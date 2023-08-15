package goldfrosttest;

//import DBconnection.DataBase;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import systemconsole.SignUP;

public class RegistrationFXMLController implements Initializable {

   SignUP sign = new SignUP();

    @FXML
    private JFXButton SignInBtn;

    @FXML
    private JFXButton Registerbtn;
    
    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXPasswordField confpassword;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXComboBox<String> jobTitle;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    jobTitle.getItems().removeAll(jobTitle.getItems());
      jobTitle.getItems().addAll("ادمن", "محاسب");
      jobTitle.getSelectionModel().select(jobTitle.getValue());
}
    

     @FXML
    void regAction(ActionEvent event) throws SQLException {
           Window owner = Registerbtn.getScene().getWindow();

         if (event.getSource() == Registerbtn){
           
        if (firstName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال الاسم الاول");
            return;
        }

        if (lastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال الاسم التانى");
            return;
        }
        if (email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال البريد الالكترونى");
            return;
        }
        if (phone.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء ادخال رقم الهاتف");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء ادخال الرقم السرى");
            return;
        }
        if (confpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء تأكيد الرقم السرى");
            return;
        }
        if (jobTitle.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء ادخال المسمى الوظيفى");
            return;
        }
        if(!password.getText().equals(confpassword.getText())){
              showAlert(Alert.AlertType.ERROR, owner,"خطأ بالتسجيل!",
                "برجاء قم بالتأكد ان الرقم السرى تم ادخاله بشكل صحيح ");
            return;
        }
             sign.Registration(firstName.getText(), lastName.getText(), phone.getText(), email.getText(), jobTitle.getValue(), password.getText(), confpassword.getText());
              showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تم تسجيلك بنجاح!",
            "مرحبا بك فى جرين فروست " + firstName.getText());
              
              firstName.clear();
              lastName.clear();
              phone.clear();
              email.clear();
              password.clear();
              confpassword.clear();
              jobTitle.valueProperty().set(null);
         
         }
         else{
                 showAlert(Alert.AlertType.ERROR, owner, "خطأ بالتسجيل!",
                "برجاء التأكد من ان الرقم السرى صحيح ");
            return;
         }
         
           
    }
    
      
    @FXML
    void signinAction(ActionEvent event) {
        Stage stage;
            if (event.getSource() == SignInBtn) {
                try {
                stage = (Stage) SignInBtn.getScene().getWindow();
                Pane pane = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
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
