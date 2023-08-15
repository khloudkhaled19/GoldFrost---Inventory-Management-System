
package goldfrosttest;

//import static LoginFXMLController.user;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Window;
import systemconsole.items;
import systemconsole.Inventory;
import systemconsole.inventories;
import systemconsole.report;
public class AddItemsFXMLController implements Initializable {
 
    items item = new items();
    
    @FXML
    private TextField incomeCodeField;
    @FXML
    private TextField incomeField;
    @FXML
    private TextField itemField;
    @FXML
    private TextField clientNameField;
    @FXML
    private TextField creditField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField outcomeField;
    @FXML
    private TextField yearField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField dayField;
    @FXML
    private TextField carriage;

    @FXML
    private JFXButton additembtn;
    @FXML
    private TableView<inventories> itemTable;
    @FXML
    private TableColumn<inventories, String> tableName;
    @FXML
    private TableColumn<inventories, String> itemName;
    @FXML
    private TableColumn<inventories, Double> income;
    @FXML
    private TableColumn<inventories, String> incomecode;
      @FXML
    private TableColumn<inventories, Double> outcome;

    @FXML
    private TableColumn<inventories, Double> price;

    @FXML
    private TableColumn<inventories, Double> credit;
    
        @FXML
    private TableColumn<inventories, String> date;

    
    ObservableList<inventories> listM ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {



         

    }    

    @FXML
    private void additembtnAction(ActionEvent event) throws Exception {
    Window owner = additembtn.getScene().getWindow();

        if(event.getSource() == additembtn){
            
            String client = clientNameField.getText();
            String items = itemField.getText();
            double incomes = Double.valueOf(incomeField.getText());
            String inCode = incomeCodeField.getText();
            double outcomes = Double.valueOf(outcomeField.getText());
            double prices =  Double.valueOf(priceField.getText());
            double credits = Double.valueOf(creditField.getText());
            String day =  dayField.getText();
            String month =  monthField.getText();
            String year = yearField.getText();
            double Carriage = Double.valueOf(carriage.getText());
            
          // Window owner = additembtn.getScene().getWindow();
         //System.out.println(user);
           
        if (clientNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال اسم العميل!");
            return;
        }
        if (itemField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  البضاعة");
            return; 
        }
        if (incomeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  الوارد");
            return; 
        }
        if (incomeCodeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  اذن الوارد");
            return; 
        }        
        if (outcomeField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  الصادر");
            return; 
        }  
        if (priceField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  السعر");
            return; 
        }    
        if (creditField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  الكمية");
            return; 
        }  
        if (dayField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  اليوم");
            return; 
        }  
        if (monthField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  الشهر");
            return; 
        }  
        if (yearField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  السنة");
            return; 
        }  
        if (carriage.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "هناك خطأ فى التسجيل!",
                "برجاء ادخال  التداول");
            return; 
        }          
            item.addNewItem(client, items, incomes ,inCode ,outcomes , prices,credits ,day,month, year, Carriage );
             showAlert(Alert.AlertType.CONFIRMATION, owner, "لقد تم العملية بنجاح!",
            "تمت اضافة البضاعة للعميل " + clientNameField.getText());
             
             clientNameField.clear();
             itemField.clear();
             incomeField.clear();
             incomeCodeField.clear();
             outcomeField.clear();
             priceField.clear();
             creditField.clear();
             dayField.clear();
             monthField.clear();
             yearField.clear();
             carriage.clear();
             
             
             
             
             
        }else{
               showAlert(Alert.AlertType.ERROR, owner, "خطأ !",
                "لم يتم اضافة البضاعة الرجاء التأكد من صحة البيانات");
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
        
        @FXML
    void clientNameAction(MouseDragEvent event) {
          tableName.setCellValueFactory(new PropertyValueFactory<inventories, String>("clientName"));
         itemName.setCellValueFactory(new PropertyValueFactory<inventories, String>("item"));
         date.setCellValueFactory(new PropertyValueFactory<inventories, String>("manualDate"));
         credit.setCellValueFactory(new PropertyValueFactory<inventories, Double>("credit"));
         price.setCellValueFactory(new PropertyValueFactory<inventories, Double>("price"));
         income.setCellValueFactory(new PropertyValueFactory<inventories, Double>("income"));
         outcome.setCellValueFactory(new PropertyValueFactory<inventories, Double>("outcome"));
         incomecode.setCellValueFactory(new PropertyValueFactory<inventories, String>("incomeCode"));
         
         listM = Inventory.ShowclientInventory(clientNameField.getText());
         itemTable.setItems(listM); 
    }
    
}
