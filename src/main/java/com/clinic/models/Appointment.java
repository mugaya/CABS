
package com.clinic.models;

import java.time.LocalDate;
import java.time.LocalTime;

/*
This class handles all the appointments
Takes the arguments
- Patient
- Doctor
- Date
- Time
 */
public class Appointment {

    private final String patient;
    private final String doctor;
    private final LocalDate date;
    private final LocalTime time;

    public Appointment(String patient, String doctor, LocalDate date, LocalTime time){
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public String getPatient(){ return patient; }
    public String getDoctor(){ return doctor; }
    public LocalDate getDate(){ return date; }
    public LocalTime getTime(){ return time; }

    @Override
    public String toString(){
        return patient + " | " + doctor + " | " + date + " " + time;
    }
}
