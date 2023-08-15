
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class AdminReportsFXMLController implements Initializable {
    
      @FXML
    private JFXButton yearlybtn;

    @FXML
    private JFXButton monthlybtn;

    @FXML
    private JFXButton dailybtn;

    @FXML
    private BorderPane showreports;

    @FXML
    void dailybtnAction(ActionEvent event) throws MalformedURLException, IOException {
           
    if(event.getSource() == dailybtn){
            System.out.print("you Clicked me !");
  
          // FXMLLoader loader = new FXMLLoader();
        //   loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/dailyReportsFXML.fxml"));
           Pane pane = FXMLLoader.load(getClass().getResource("dailyReportsFXML.fxml"));            
           showreports.setCenter(pane);
          //  Scene scene = new Scene(pane);

            
        } 
    }

    @FXML
    void monthlybtnAction(ActionEvent event) throws MalformedURLException, IOException {
         if(event.getSource() == monthlybtn){
            System.out.print("you Clicked me !");
        //   FXMLLoader loader = new FXMLLoader();
        //   loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/monthlyReportsFXML.fxml"));
            Pane pane2 = FXMLLoader.load(getClass().getResource("monthlyReportsFXML.fxml"));
            showreports.setCenter(pane2);
          //  Scene scene = new Scene(pane);

       }
    }
    
        @FXML
    void yearlybtnAction(ActionEvent event) throws MalformedURLException, IOException {
               if(event.getSource() == yearlybtn){
            System.out.print("you Clicked me !");
           //FXMLLoader loader = new FXMLLoader();
           //loader.setLocation(new URL("file:///C:/Users/Salma/Documents/NetBeansProjects/SystemConsole/src/GUI/AdminFXML/yearlyReportsFXML.fxml"));
            Pane pane3 = FXMLLoader.load(getClass().getResource("yearlyReportsFXML.fxml"));
            showreports.setCenter(pane3);
          //  Scene scene = new Scene(pane);

       }
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
