package com.omeasraf.Game;

import com.omeasraf.Game.model.Connect4Status;
import com.jbbwebsolutions.http.utility.URLUtility;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class controller implements Initializable {
    private Color player1 = Color.valueOf("#faff03");
    private Color player2 = Color.valueOf("#00ff51");

    private SimpleObjectProperty<Color> playerColorProperty = new SimpleObjectProperty<Color>(player1);
    private final int gridColumnCount = 6;
    private final int gridRowCount = 7;
    private int counter = 0;
    private char[] moves = new char[42];

    @FXML
    private GridPane gridPane;

    @FXML
    private Button btnReset;

    @FXML
    private Label lblTitle;

    @FXML
    private Circle playerCircle;

    @FXML
    void onReset(ActionEvent event) {
        counter = 0;
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().addAll(generateColumn());
        gridPane.getRowConstraints().addAll(generateRow());
        createGrids(gridPane);

        for (int i = 0; i < 42; i++){
            moves[i] = '?';
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 42; i++){
            moves[i] = '?';
        }
        gridPane.getStyleClass().add("gridPane");
        gridPane.getColumnConstraints().addAll(generateColumn());
        gridPane.getRowConstraints().addAll(generateRow());
        createGrids(gridPane);
    }


    private List<RowConstraints> generateRow(){
        List<RowConstraints> allRows = new ArrayList<>();
        for (int i = 0; i < gridRowCount; i++)
            allRows.add(new RowConstraints(100,100,Double.MAX_VALUE));
        return allRows;
    }
    private List<ColumnConstraints> generateColumn(){
        List<ColumnConstraints> allRows = new ArrayList<>();
        for (int i = 0; i < gridColumnCount; i++)
            allRows.add(new ColumnConstraints(100,100,Double.MAX_VALUE));
        return allRows;
    }


    private void createGrids(final GridPane gridpane){

        double height = 100.0;
        double width = 100.0;
        gridpane.getChildren().clear();
        for(int i =0; i < gridpane.getColumnConstraints().size(); i++){
            for(int g =0; g < gridpane.getRowConstraints().size(); g++){
                Rectangle rect = new Rectangle(width, height);
                Circle circle = new Circle(50);
                circle.centerXProperty().set(50);
                circle.centerYProperty().set(50);
                circle.setFill(Color.WHITE);
                Shape cell = Path.subtract(rect, circle);
                cell.getStyleClass().add("cell");


                final Circle diskPreview = new Circle(45);
                diskPreview.setOpacity(1);
                diskPreview.setFill(Color.WHITE);

                diskPreview.setOnMouseEntered(arg0 -> {

                    diskPreview.setFill(Color.WHITE);
                    if(playerColorProperty.get()==player1){
                        diskPreview.setFill(player1);
                    }else{
                        diskPreview.setFill(player2);
                    }
                });

                diskPreview.setOnMouseExited(arg0 -> diskPreview.setFill(Color.WHITE));

                final Circle disk = new Circle(45);
                disk.fillProperty().bind(playerColorProperty);
                disk.setOpacity(0);
                disk.setTranslateY(-(height *(i +1)));
                disk.setId(Integer.toString(counter));
                counter++;

                final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), disk);



                disk.setOnMouseClicked(arg0 ->{

                    disk.setOpacity(1);
                    diskTranslateY(disk, translateTransition);
                });

                diskPreview.setOnMouseClicked(arg0 -> {

                    moves[Integer.parseInt(disk.getId())] = playerColorProperty.get() == player1 ? 'X' : 'O';
                    disk.setOpacity(1);
                    diskTranslateY(disk, translateTransition);
//                    System.out.println(moves);
                    checkWinner(new String(moves));
                });


                StackPane stack = new StackPane();
                stack.getChildren().addAll(cell, diskPreview, disk);
                gridpane.add(stack, g, i);

            }

        }
    }

    private void checkWinner(String moves){
        final String url = "http://localhost:9416/game/connect4?moves=" + moves;
        Connect4Status gameStatus = URLUtility.get(url, Connect4Status.class);

        if (gameStatus.isWinner()) {
            final boolean isPlayer = gameStatus.getWhoWon().equals("X");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Congratulations!");
            alert.setHeaderText("Winner, winner chicken dinner!");
            alert.setContentText("Player " + (isPlayer ? "Yellow" : "Green") +" won the game, congratulations!");
            Circle circle = new Circle(50);
            circle.maxWidth(10);
            circle.setFill(isPlayer ? player1 : player2);
            alert.getDialogPane().setGraphic(circle);
            alert.showAndWait();
        }
    }

    private void diskTranslateY(Circle disk, TranslateTransition translateTransition) {
        if(disk.getTranslateY()!=0){
            translateTransition.setToY(0);
            translateTransition.play();
            if(playerColorProperty.get()== player1){
                playerColorProperty.set(player2);
                playerCircle.setFill(player2);
                disk.fillProperty().bind(new SimpleObjectProperty<>(player1));
            }else{
                playerColorProperty.set(player1);
                playerCircle.setFill(player1);
                disk.fillProperty().bind(new SimpleObjectProperty<>(player2));
            }
        }
    }
}
