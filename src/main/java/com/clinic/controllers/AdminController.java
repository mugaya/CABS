
package com.clinic.controllers;

import com.clinic.services.ClinicService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminController {

    @FXML private ListView<String> doctorList;
    @FXML private TextField doctorName;

    private static ClinicService service = new ClinicService();

    private void alert(String t){
        new Alert(Alert.AlertType.INFORMATION, t).show();
    }

    @FXML
    public void initialize(){
        doctorList.getItems().addAll(service.getDoctors());
    }

    @FXML
    public void addDoctor(){
        if(doctorName.getText().isEmpty()){
            alert("Enter doctor name");
            return;
        }
        service.addDoctor(doctorName.getText());
        doctorList.getItems().add(doctorName.getText());
        alert("Doctor Added");
        doctorName.clear();
    }

    @FXML
    public void removeDoctor(){
        String selected = doctorList.getSelectionModel().getSelectedItem();
        if(selected == null){
            alert("Select a doctor");
            return;
        }
        service.removeDoctor(selected);
        doctorList.getItems().remove(selected);
        alert("Doctor Removed");
    }
}
