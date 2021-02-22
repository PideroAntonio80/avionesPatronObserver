package com.sanvalero.planesanimatedpsp;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

/**
 * Creado por @ author: Pedro Orós
 * el 21/02/2021
 */
public class AppController {

    public AnchorPane air;
    public Polygon azul;
    public Polygon rojo;

    Target target;
    Predator predator;

    private double speed;

    private double xAzul = 233;
    private double yAzul = 450;

    private double xRojo = 233;
    private double yRojo = 530;

    public AppController() {
        air = new AnchorPane();
        azul = new Polygon();
        rojo = new Polygon();

        this.speed = 20;

        target = new Target(xAzul, yAzul, 55, azul);
        predator = new Predator(rojo);
        target.addPredator(predator);
    }

    @FXML
    public void up(Event event) {

        target.down(speed);
        System.out.println(target.getHeight());
        System.out.println(predator.getHeight());
        System.out.println(azul.getLayoutX());

        azul.setLayoutY(target.getHeight());
        rojo.setLayoutY(predator.getHeight() - 80);

    }

    @FXML
    public void down(Event event) {
        target.up(speed);

        azul.setLayoutY(target.getHeight());
        rojo.setLayoutY(predator.getHeight() - 80);
    }

    @FXML
    public void left(Event event) {
        target.setDirection("left");

        xAzul -= speed;
        xRojo -= speed;

        azul.setLayoutX(xAzul);
        rojo.setLayoutX(xRojo);
    }

    @FXML
    public void right(Event event) {
        target.setDirection("right");

        xAzul += speed;
        xRojo += speed;

        azul.setLayoutX(xAzul);
        rojo.setLayoutX(xRojo);
    }

    @FXML
    public void fast(Event event) {
        speed = speed * 2;
    }

    @FXML
    public void slow(Event event) {
        speed = speed / 2;
    }


}
