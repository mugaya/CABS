package com.clinic.controllers;

import com.clinic.MainApp;
import com.clinic.models.Patient;
import com.clinic.services.ClinicService;
import com.clinic.services.SessionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/*
Login controller Class for the login logic
Has the methods;
- login
- register
- setSession
 */
public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private static final ClinicService service = new ClinicService();

    private void alert(String text){
        new Alert(Alert.AlertType.INFORMATION, text).show();
    }

    @FXML
    public void login(){
        try{
            if(service.isAdmin(usernameField.getText(), passwordField.getText())){
                setSession(usernameField.getText());
                Stage stage = MainApp.stage;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Admin.fxml"));
                stage.setScene(new Scene(loader.load()));
                return;
            }

            Patient p = service.loginPatient(usernameField.getText(), passwordField.getText());
            if(p != null){
                setSession(usernameField.getText());
                Stage stage = MainApp.stage;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Patient.fxml"));
                stage.setScene(new Scene(loader.load()));
            } else alert("Invalid login");
        }catch(Exception e){
            alert("Error logging in" + e);
        }
    }

    @FXML
    public void register(){
        if(service.registerPatient(usernameField.getText(), passwordField.getText()))
            alert("Registered Successfully");
        else
            alert("Registration Failed");
    }

    private void setSession(String username){
        // SAVE LOGGED IN PATIENT
        SessionService.setUser(username);
    }
}
