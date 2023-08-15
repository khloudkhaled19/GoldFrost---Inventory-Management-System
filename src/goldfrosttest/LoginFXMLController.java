package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import systemconsole.SignUP;
import static systemconsole.SignUP.driver;
import static systemconsole.SignUP.password;
import static systemconsole.SignUP.url;
import static systemconsole.SignUP.username;
import systemconsole.employee;
public class LoginFXMLController  implements Initializable{
      SignUP sign = new SignUP();
    @FXML
    private JFXButton signupbtn;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton loginbtn;
    
     String admin = "ادمن";
     String accountant = "محاسب";
     String labror = "عامل";
 
  public static HashMap<String , String> user = new HashMap<>(); 
     
    @FXML
    void loginAction(ActionEvent event) throws SQLException, IOException {
         Stage stage;
        if(event.getSource() == loginbtn){
          Window owner = loginbtn.getScene().getWindow();
       //  System.out.println(user);
           
        if (usernameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال اسم المستخدم!");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال الرقم السرى");
            return; 
        }
               String admin = "ادمن";
               String accountant = "محاسب";
               String labror = "عامل";
          stage = (Stage) loginbtn.getScene().getWindow();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUP.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        
        Statement statement = con.createStatement();
 
        ResultSet results = statement.executeQuery("SELECT * FROM الموظفون  WHERE firstName = '"+usernameField.getText()+"' AND password ='"+passwordField.getText()+"' ");
        if(results.next()){
          String  firstName = results.getString("firstName");
             //lastName = results.getString("lastName");
            System.out.println("welcome "+firstName+ " to the system " );
        
         if(admin.equals(results.getString("jobTitle"))){
             user.put(passwordField.getText(),usernameField.getText());
             Pane pane = FXMLLoader.load(getClass().getResource("AdminPanelFXML.fxml"));
             Scene scene = new Scene(pane);
             stage.setScene(scene);
             stage.show();
          System.out.println("welcome to the admin panel" );
         }
         else if(accountant.equals(results.getString("jobTitle"))){
             user.put(passwordField.getText(),usernameField.getText());
             Pane pane = FXMLLoader.load(getClass().getResource("accountantPanelFXML.fxml"));
             Scene scene = new Scene(pane);
             stage.setScene(scene);
             stage.show();   
         
          System.out.println("welcome to the accountant panel" );
         }
        }
        else{
             showAlert(Alert.AlertType.ERROR, owner, "المستخدم غير موجود!",
                "برجاء التأكد من اسم المستخدم و الرقم السرى!");
            return;
        }

          }
    }

    @FXML
    void signupAction(ActionEvent event) {
        Stage stage;
            if (event.getSource() == signupbtn) {
                try {
                     stage = (Stage) signupbtn.getScene().getWindow();         
                     Pane pane = FXMLLoader.load(getClass().getResource("RegistrationFXML.fxml"));
                     Scene scene = new Scene(pane);
                     stage.setScene(scene);
                     stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
             }  
    }
    
      public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
  
    }

}