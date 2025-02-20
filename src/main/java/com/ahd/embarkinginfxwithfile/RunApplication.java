package com.ahd.embarkinginfxwithfile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class RunApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("Dress-FXML.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Embarking In FX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String sceneName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource(sceneName + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setScene(scene);
        } catch (IOException ex){
            ex.printStackTrace();
            System.err.println("Scene not show");
        }
    }
}