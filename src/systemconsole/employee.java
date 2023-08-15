package systemconsole;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class employee {
  private  StringProperty fullName,firstName, lastName,phone ,jobTitle,email, Password,confpassword;
  
     public StringProperty getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
    
    public StringProperty fullNameProperty() { return fullName; } 


    public  String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public StringProperty firstNameProperty() { return firstName; } 


    public  String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public StringProperty lastNameProperty() { return lastName; } 


    public  String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public StringProperty phoneProperty() { return phone; } 


    public  String getJobTitle() {
        return jobTitle.get();
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }
    
    public StringProperty jobTitleProperty() { return jobTitle; } 


    public  String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public StringProperty emailProperty() { return email; } 


    public  String getPassword() {
        return Password.get();
    }

    public void setPassword(String Password) {
        this.Password.set(Password);
    }
    
    public StringProperty passwordProperty() { return Password; } 


    public  StringProperty getConfpassword() {
        return confpassword;
    }

    public void setConfpassword(StringProperty confpassword) {
        this.confpassword = confpassword;
    }
    
    public StringProperty confpasswordProperty() { return confpassword; } 
    
    public employee(){
        
    }
    public employee(String firstName, String Password){
        this.firstName = new SimpleStringProperty(firstName) ;
        this.Password = new SimpleStringProperty(Password);

    }

    public employee(String firstName, String lastName, String phone, String jobTitle, String email, String Password, String confpassword) {
        this.firstName = new SimpleStringProperty(firstName) ;
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.jobTitle = new SimpleStringProperty(jobTitle);
        this.email = new SimpleStringProperty(email);
        this.Password = new SimpleStringProperty(Password);
        this.confpassword = new SimpleStringProperty(confpassword);
    }
  
        public employee(String firstName, String lastName, String phone, String jobTitle, String email, String Password) {
        this.firstName = new SimpleStringProperty(firstName) ;
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.jobTitle = new SimpleStringProperty(jobTitle);
        this.email = new SimpleStringProperty(email);
        this.Password = new SimpleStringProperty(Password);
    } 
      
        public employee(String fullName, String phone, String email, String jobTitle) {
        this.fullName = new SimpleStringProperty(fullName) ;
        this.phone = new SimpleStringProperty(phone);
        this.jobTitle = new SimpleStringProperty(jobTitle);
        this.email = new SimpleStringProperty(email);
    }     
}
