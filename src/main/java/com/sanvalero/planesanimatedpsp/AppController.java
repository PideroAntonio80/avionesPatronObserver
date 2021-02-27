package com.sanvalero.planesanimatedpsp;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/**
 * Creado por @ author: Pedro Orós
 * el 21/02/2021
 */
public class AppController {

    public AnchorPane air;
    public Polygon azul;
    public Polygon rojo;
    public Button bPause, bKill;
    public TextField tfSpeed;
    public Label lGameOver;

    Target target;
    Predator predator;

    private double speed;

    private double xAzul;
    private double yAzul;

    private double xRojo;
    private double yRojo;

    Thread hiloUpT;
    Thread hiloUpP;

    public AppController() {
        air = new AnchorPane();
        azul = new Polygon();
        rojo = new Polygon();

        xAzul = 400;
        yAzul = 500;
        xRojo = 400;
        yRojo = 580;

        air.setPrefWidth(842);
        air.setPrefHeight(670);

        azul.setLayoutX(xAzul);
        azul.setLayoutY(yAzul);
        rojo.setLayoutX(xRojo);
        rojo.setLayoutY(yRojo);

        this.speed = 1;

        target = new Target(yAzul, speed, xAzul, speed, this);
        predator = new Predator();
        target.addPredator(predator);

        hiloUpT = new Thread(target);
    }

    @FXML
    public void start() {
        lGameOver.setText("");
        azul.setLayoutX(xAzul);
        azul.setLayoutY(yAzul);
        rojo.setLayoutX(xRojo);
        rojo.setLayoutY(yRojo);

        rojo.setVisible(true);

        target.turnOn();
        target.goUp();

        hiloUpT.start();
    }

    @FXML
    public void pause() {
        target.pause();

        if(bPause.getText().equals("PAUSE")) {
            bPause.setText("CONTINUE");
        }
        else if(bPause.getText().equals("CONTINUE")) {
            bPause.setText("PAUSE");
        }
    }

    @FXML
    public void stop() {
        target.finish();

        gameOver();
    }

    @FXML
    public void kill() {
        target.removePredator(predator);
        rojo.setVisible(false);
        bKill.setText("BOOM");
        transitionButtonKill(3);
    }

    @FXML
    public void up(Event event) {
        target.goUp();
    }

    @FXML
    public void down(Event event) {
        target.goDown();
    }

    @FXML
    public void left(Event event) {
        target.goLeft();
    }

    @FXML
    public void right(Event event) {
        target.goRight();
    }

    @FXML
    public void fast(Event event) {
        target.goFast();
    }

    @FXML
    public void slow(Event event) {
        target.goSlow();
    }

    public void transitionButtonKill(int segundos) {
        bKill.setVisible(true);
        PauseTransition visibleBoom = new PauseTransition((Duration.seconds(segundos)));
        visibleBoom.setOnFinished(event -> bKill.setText("KILL PREDATOR"));
        visibleBoom.play();
    }

    public void gameOver() {
        lGameOver.setText("GAME OVER");

        target = new Target(yAzul, speed, xAzul, speed, this);
        predator = new Predator();
        target.addPredator(predator);

        hiloUpT = new Thread(target);
    }
}
