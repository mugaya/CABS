
package com.clinic.services;

import com.clinic.models.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

// Business logic layer
public class ClinicService {

    private static List<Patient> patients = new ArrayList<>();
    private static List<String> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    // Fixed time slots daily
    private static List<LocalTime> slots = Arrays.asList(
        LocalTime.of(9,0),
        LocalTime.of(10,0),
        LocalTime.of(11,0),
        LocalTime.of(12,0),
        LocalTime.of(14,0),
        LocalTime.of(15,0),
        LocalTime.of(16,0)
    );

    static{
        doctors.add("Dr. Newton");
        doctors.add("Dr. Daisy");
        doctors.add("Dr. Mugaya");
    }

    public boolean registerPatient(String username, String password){
        try{
            if(username == null || password == null){
                return false;
            }
            patients.add(new Patient(username, password));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Patient loginPatient(String username, String password){
        for(Patient p : patients){
            if(p.getUsername().equals(username) && p.getPassword().equals(password))
                return p;
        }
        return null;
    }

    // we use this to determine admins - Hard code username and password
    public boolean isAdmin(String u, String p){

        return u.equals("admin") && p.equals("admin123");
    }

    public List<String> getDoctors(){ return doctors; }
    public void addDoctor(String d){ doctors.add(d); }
    public void removeDoctor(String d){ doctors.remove(d); }

    public List<LocalTime> getSlots(){ return slots; }
    public List<Appointment> getAppointments(){ return appointments; }

    // Check if slot is already booked
    public boolean isSlotTaken(String doctor, LocalDate date, LocalTime time){
        for(Appointment a : appointments){
            if(a.getDoctor().equals(doctor) && a.getDate().equals(date) && a.getTime().equals(time))
                return true;
        }
        return false;
    }

    // Handle all bookings
    public Appointment book(String patient, String doctor, LocalDate date, LocalTime time){
        Appointment a = new Appointment(patient, doctor, date, time);
        appointments.add(a);
        return a;
    }

    // Handle all cancellations to allow for rescheduling
    public Appointment cancel(String patient, String doctor, LocalDate date, LocalTime time){
        Appointment a = new Appointment(patient, doctor, date, time);
        appointments.remove(a);
        return a;
    }
}
