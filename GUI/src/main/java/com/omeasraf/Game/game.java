package com.omeasraf.Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class game extends Application {


        public static void main(String[] args) {
            Application.launch(game.class, args);
        }

        @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("gameView.fxml"));
            stage.setTitle("Connect 4");

            Scene scene = new Scene (root);
            stage.setScene(scene);

            URL url = this.getClass().getResource("Connect4.css");
            if (url == null) {
                System.out.println("Unable to retrieve Connect4.css, please make sure the file exists.");
                System.exit(-1);
            }

            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
            stage.setMaxWidth(717.0);
            stage.setMinWidth(717.0);
            stage.setResizable(false);
            stage.show();
        }



}
