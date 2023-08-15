
package goldfrosttest;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class ItemsFXMLController implements Initializable {

    @FXML
    private JFXButton outcomebtn;
    @FXML
    private JFXButton incomebtn;
    @FXML
    private JFXButton delitemsbtn;
    @FXML
    private JFXButton addItemsbtn;
    @FXML
    private BorderPane menuPane;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void outcomebtnAction(ActionEvent event) throws IOException {
        if(event.getSource() == outcomebtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("outcomeItemsFXML.fxml"));            
            menuPane.setCenter(pane);            
        } 
    }

    @FXML
    private void incomebtnAction(ActionEvent event) throws IOException {
        if(event.getSource() == incomebtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("incomeItemsFXML.fxml"));            
            menuPane.setCenter(pane);
        } 
    }

    @FXML
    private void delitemsbtnAction(ActionEvent event) throws MalformedURLException, IOException {
        if(event.getSource() == delitemsbtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("deleteItemsFXML.fxml"));            
            menuPane.setCenter(pane);
        } 
    }

    @FXML
    private void addItemsbtnAction(ActionEvent event) throws MalformedURLException, IOException {
        if(event.getSource() == addItemsbtn){
            System.out.print("you Clicked me !");
            Pane pane = FXMLLoader.load(getClass().getResource("addItemsFXML.fxml"));            
            menuPane.setCenter(pane);
        } 
    }
    
}
