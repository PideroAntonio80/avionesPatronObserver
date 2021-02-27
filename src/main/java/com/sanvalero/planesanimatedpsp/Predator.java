package com.sanvalero.planesanimatedpsp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Creado por @ author: Pedro Orós
 * el 21/02/2021
 */
public class Predator implements PropertyChangeListener {

    private double height;
    private double speed;
    private double degrees;
    private String direction;

    public Predator() {
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        switch (event.getPropertyName()) {
            case "height":
                this.height = (double) event.getNewValue();
                break;

            case "speed":
                this.speed = (double) event.getNewValue();
                break;

            case "degrees":
                this.degrees = (double) event.getNewValue();
                break;

            case "direction":
                this.direction = (String) event.getNewValue();

            default:
                System.out.println("Keep Going");
                break;
        }
    }
}
