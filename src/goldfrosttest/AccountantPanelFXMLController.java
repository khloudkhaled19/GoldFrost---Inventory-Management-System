
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import systemconsole.SignUP;



public class AccountantPanelFXMLController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private JFXButton homebtn;

    @FXML
    private JFXButton itemsbtn;

    @FXML
    private JFXButton invoicebtn;

    @FXML
    private JFXButton clientsbtn;

    @FXML
    private JFXButton settingsbtn;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    void clientsbtnAction(ActionEvent event) throws IOException {
        if(event.getSource() == clientsbtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("clientsFXML.fxml"));
            mainPane.setCenter(pane);
        }  
    }

    @FXML
    void homebtnAction(ActionEvent event) throws MalformedURLException, IOException {
        if(event.getSource() == homebtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("accountantHomeFXML.fxml"));
            mainPane.setCenter(pane);
        }  
    }

    @FXML
    void invoicebtnAction(ActionEvent event) throws IOException {
        if(event.getSource() == invoicebtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("invoiceFXML.fxml"));
            mainPane.setCenter(pane);
        }  
    }

    @FXML
    void itemsbtnAction(ActionEvent event) throws MalformedURLException, IOException {
        if(event.getSource() == itemsbtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("itemsFXML.fxml"));
            mainPane.setCenter(pane);
        }  
    }

    @FXML
    void logoutbtnAction(ActionEvent event) throws IOException {
     Stage stage;
        if(event.getSource() == logoutbtn){
              SignUP.logout();
              stage = (Stage) logoutbtn.getScene().getWindow();
              Pane pane = FXMLLoader.load(getClass().getResource("RegistrationFXML.fxml"));
              Scene scene = new Scene(pane);
              stage.setScene(scene);
              stage.show();
            }
    }
    @FXML
    void settingsbtnAction(ActionEvent event) throws IOException {
        if(event.getSource() == settingsbtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("updateAccountantFXML.fxml"));
            mainPane.setCenter(pane);
        } 
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           Pane pane = FXMLLoader.load(getClass().getResource("accountantHomeFXML.fxml"));
            mainPane.setCenter(pane);  
        }catch (IOException ex) {
            Logger.getLogger(AccountantPanelFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
              }    
    
}
