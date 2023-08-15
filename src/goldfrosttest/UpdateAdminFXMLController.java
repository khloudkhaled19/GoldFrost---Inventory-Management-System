
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import goldfrosttest.LoginFXMLController;
import java.sql.SQLException;
import systemconsole.Employees;
import goldfrosttest.LoginFXMLController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static systemconsole.Reports.driver;
import static systemconsole.Reports.password;
import static systemconsole.Reports.url;
import static systemconsole.Reports.username;
import systemconsole.report;


public class UpdateAdminFXMLController implements Initializable {
    @FXML
    private JFXButton firstNameUpdatebtn;

    @FXML
    private TextField firstNameField;

    @FXML
    private JFXButton lastNameUpdatebtn;

    @FXML
    private TextField lastNameField;

    @FXML
    private JFXButton phoneUpdatebtn;

    @FXML
    private TextField phoneField;

    @FXML
    private JFXButton emailUpdatebtn;

    @FXML
    private TextField emailField;

    @FXML
    private JFXButton passwordUpdatebtn;

    @FXML
    private PasswordField passwordField;
    
    

    @FXML
    void emailUpdatebtnAction(ActionEvent event) throws SQLException {
       if(event.getSource() == emailUpdatebtn){
          String fn = emailField.getText();
          Employees.updateEmail(fn);
       }
    }

    @FXML
    void firstNameUpdatebtnAction(ActionEvent event) throws SQLException {
       if(event.getSource() == firstNameUpdatebtn){
          String fn = firstNameField.getText();
          Employees.updatefirstName(fn);
       }

    }

    @FXML
    void lastNameUpdatebtnAction(ActionEvent event) throws SQLException {
          if(event.getSource() == lastNameUpdatebtn){
          String fn = lastNameField.getText();
          Employees.updatelastName(fn);
       }
    }

    @FXML
    void passwordUpdatebtnAction(ActionEvent event) throws SQLException {
        if(event.getSource() == passwordUpdatebtn){
          String fn = passwordField.getText();
          Employees.updatepassword(fn);
       }
    }

    @FXML
    void phoneUpdatebtnAction(ActionEvent event) throws SQLException {
       if(event.getSource() == phoneUpdatebtn){
          String fn = phoneField.getText();
          Employees.updatephone(fn);
       }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // LoginFXMLController.map.entrySet();
    String fn = LoginFXMLController.user.get(LoginFXMLController.user.keySet().toArray()[0]);
    String pass =LoginFXMLController.user.get(LoginFXMLController.user.values().toArray()[0]);
    String pass2 = LoginFXMLController.user.keySet().toString();
     firstNameField.setText(fn);
     pass2 = pass2.replaceAll("[\\[\\](){}]","");
     System.out.print("the password is :" + pass2);
     
      try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found "+ e);
        }
        try {
            Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/registrationinventory?useUnicode=true&characterEncoding=utf8",username,password);

            Statement stmt = con.createStatement();
            String data = "SELECT * FROM الموظفون WHERE password = '"+pass2+"' ";
            ResultSet rs = stmt.executeQuery(data);
            while (rs.next()) {
             String ln = rs.getString("lastName");
             String pn = rs.getString("phone");
             String e = rs.getString("email");
             String up = rs.getString("password");
             
            lastNameField.setText(ln);
            phoneField.setText(pn);
            emailField.setText(e);
            passwordField.setText(up);
            
            }
           
           

        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
    }    
    
}
