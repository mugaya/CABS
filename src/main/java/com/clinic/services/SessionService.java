package com.clinic.services;
/*
This class is for storing the currently logged-in user
Used for attaching their details to other functions e.g.
Bookings, Cancellations, Rescheduling
 */
public class SessionService {

    private static String currentUser;

    public static void setUser(String user){
        currentUser = user;
    }

    public static String getUser(){
        return currentUser;
    }
}