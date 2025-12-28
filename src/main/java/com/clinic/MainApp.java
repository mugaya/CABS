
package com.clinic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
This is the main class for the application
 */
public class MainApp extends Application {

    public static Stage stage;

    @Override
    public void start(Stage s) throws Exception {
        stage = s;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Clinic Appointment Booking System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
