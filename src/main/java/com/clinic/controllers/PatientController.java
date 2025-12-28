
package com.clinic.controllers;

import com.clinic.models.Appointment;
import com.clinic.services.ClinicService;
import com.clinic.services.SessionService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatientController {

    @FXML private ListView<String> doctorList;
    @FXML private ListView<LocalTime> slotList;
    @FXML private DatePicker datePicker;
    @FXML private ListView<Appointment> appointmentList;

    String patient = SessionService.getUser();

    private static final ClinicService service = new ClinicService();

    private void alert(String message){
        new Alert(Alert.AlertType.INFORMATION, message).show();
    }

    @FXML
    public void initialize(){
        doctorList.getItems().addAll(service.getDoctors());
        slotList.getItems().addAll(service.getSlots());
        appointmentList.getItems().addAll(service.getAppointments());
    }

    @FXML
    public void book(){
        try{
            String doctor = doctorList.getSelectionModel().getSelectedItem();
            LocalDate date = datePicker.getValue();
            LocalTime slot = slotList.getSelectionModel().getSelectedItem();

            if(doctor == null || date == null || slot == null){
                alert("Please select doctor, date and slot");
                return;
            }

            if(date.isBefore(LocalDate.now())){
                alert("Cannot book past date");
                return;
            }

            if(service.isSlotTaken(doctor, date, slot)){
                alert("Slot already booked");
                return;
            }

            Appointment a = service.book(patient, doctor, date, slot);
            appointmentList.getItems().add(a);
            alert("Appointment booked successfully");

        }catch(Exception e){
            alert("Booking Failed "+ e);
        }
    }

    @FXML
    public void cancel(){

        String appointment = String.valueOf(appointmentList.getSelectionModel().getSelectedItem());

        try {

            if (appointment == null) {
                alert("Please select an existing appointment");
                return;
            }
            String[] result = appointment.split("\\|");

            String doctor = result[1];
            String[] dates = result[2].trim().split("\\s+");

            alert("Appointment cancelled successfully - " + dates[0]);

            LocalDate date = LocalDate.parse(dates[0]);
            LocalTime slot = LocalTime.parse(dates[1]);

            Appointment a = service.cancel(patient, doctor, date, slot);
            appointmentList.getItems().remove(a);
        } catch (Exception e) {
            alert("Cancellation Failed " + e);
        }
    }
}
