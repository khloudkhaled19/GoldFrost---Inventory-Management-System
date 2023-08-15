
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import systemconsole.Employees;
import systemconsole.SignUP;
public class AdminPanelFXMLController implements Initializable {

      @FXML
    private JFXButton adminHome;

    @FXML
    private JFXButton adminEmployees;

    @FXML
    private JFXButton adminReports;

    @FXML
    private JFXButton adminIncome;

    @FXML
    private JFXButton adminOutcome;

    @FXML
    private JFXButton adminSettings;

    @FXML
    private JFXButton logout;
    
    @FXML
    private BorderPane mainPane;
    
    
  
      

    @FXML
    void adminEmployeesAction(ActionEvent event) throws MalformedURLException, IOException {
            if(event.getSource() == adminEmployees){
            System.out.print("you Clicked me !");
            
            //FxmlLoader obj = new FxmlLoader();
          //  Pane view = obj.getPage("adminHomeFXML");
           
            
        //   FXMLLoader loader = new FXMLLoader();
        //   loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/adminEmployeesFXML.fxml"));
           // Pane pane = loader.<Pane>load();
          //  Scene scene = new Scene(pane);
            Pane pane = FXMLLoader.load(getClass().getResource("adminEmployeesFXML.fxml"));
            mainPane.setCenter(pane);
        } 
    }

    @FXML
    void adminHomeAction(ActionEvent event) throws MalformedURLException, IOException {
        if(event.getSource() == adminHome){
            System.out.print("you Clicked me !");
            
            //FxmlLoader obj = new FxmlLoader();
          //  Pane view = obj.getPage("adminHomeFXML");
           
            
          // FXMLLoader loader = new FXMLLoader();
          // loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/adminHomeFXML.fxml"));
          //  Pane pane = loader.<Pane>load();
          //  Scene scene = new Scene(pane);
            Pane pane = FXMLLoader.load(getClass().getResource("adminHomeFXML.fxml"));
            mainPane.setCenter(pane);
        }  
    }

    @FXML
    void adminIncomeAction(ActionEvent event) throws MalformedURLException, IOException {
               if(event.getSource() == adminIncome){
            System.out.print("you Clicked me !");
            
            //FxmlLoader obj = new FxmlLoader();
          //  Pane view = obj.getPage("adminHomeFXML");
           
            
          // FXMLLoader loader = new FXMLLoader();
        //   loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/adminIncomesFXML.fxml"));
         //   Pane pane = loader.<Pane>load();
          //  Scene scene = new Scene(pane);
        Pane pane = FXMLLoader.load(getClass().getResource("adminIncomesFXML.fxml"));
            mainPane.setCenter(pane);
        } 
    }

    @FXML
    void adminOutcomeAction(ActionEvent event) throws MalformedURLException, IOException {
              if(event.getSource() == adminOutcome){
            System.out.print("you Clicked me !");
            
            //FxmlLoader obj = new FxmlLoader();
          //  Pane view = obj.getPage("adminHomeFXML");
           
            
       //    FXMLLoader loader = new FXMLLoader();
         //  loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/adminOutcomesFXML.fxml"));
          //  Pane pane = loader.<Pane>load();
          //  Scene scene = new Scene(pane);
        Pane pane = FXMLLoader.load(getClass().getResource("adminOutcomesFXML.fxml"));
            mainPane.setCenter(pane);
        } 
    }

    @FXML
    void adminReportsAction(ActionEvent event) throws MalformedURLException, IOException {
            if(event.getSource() == adminReports){
            System.out.print("you Clicked me !");
            
            //FxmlLoader obj = new FxmlLoader();
          //  Pane view = obj.getPage("adminHomeFXML");
           
            
         //  FXMLLoader loader = new FXMLLoader();
         //  loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/adminReportsFXML.fxml"));
           //  Pane pane = loader.<Pane>load();
           // Scene scene = new Scene(vbox);
        Pane pane = FXMLLoader.load(getClass().getResource("adminReportsFXML.fxml"));
            mainPane.setCenter(pane);
        } 
    }

    @FXML
    void adminSettingsAction(ActionEvent event) throws IOException {
            if(event.getSource() == adminSettings){
            System.out.print("you Clicked me !");
            
            //FxmlLoader obj = new FxmlLoader();
          //  Pane view = obj.getPage("adminHomeFXML");
           
            
          // FXMLLoader loader = new FXMLLoader();
         //  loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/updateAdminFXML.fxml"));
         //   Pane pane = loader.<Pane>load();
          //  Scene scene = new Scene(pane);
        Pane pane = FXMLLoader.load(getClass().getResource("updateAdminFXML.fxml"));
            mainPane.setCenter(pane);
        }  
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        Stage stage;
            if(event.getSource() == logout){
                SignUP.logout();
          stage = (Stage) logout.getScene().getWindow();

                //   root = FXMLLoader.load(getClass().getClassLoader().getResource("/RegistrationFXML.fxml"));
        //          FXMLLoader loader = new FXMLLoader();
         
      //  loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/RegistrationFXML.fxml"));
        // Pane pane = loader.<Pane>load();
         Pane pane = FXMLLoader.load(getClass().getResource("RegistrationFXML.fxml"));
         Scene scene = new Scene(pane);
                 //  Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
            }
          //  System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
          try {
           Pane pane = FXMLLoader.load(getClass().getResource("adminHomeFXML.fxml"));              
           mainPane.setCenter(pane);

          } catch (IOException ex) {
              Logger.getLogger(AdminPanelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          }

        }          
    
    
}
