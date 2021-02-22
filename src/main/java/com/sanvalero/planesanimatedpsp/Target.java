package com.sanvalero.planesanimatedpsp;

import javafx.scene.shape.Polygon;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Creado por @ author: Pedro Orós
 * el 21/02/2021
 */
public class Target {
    private double height;
    private double speed;
    private double degrees;
    private String direction;
    private Polygon figura;

    private PropertyChangeSupport scape;

    public Target(double height, double speed, double degrees, Polygon figura) {
        this.height = height;
        this.speed = speed;
        this.degrees = degrees;
        this.figura = figura;
        scape = new PropertyChangeSupport(this);
    }

    public double getHeight() {
        return height;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDegrees() {
        return degrees;
    }

    public String getDirection() {
        return direction;
    }

    public void up(double value) {
        scape.firePropertyChange("height", height, (height + value));
        figura.setLayoutX(height + value);
        height += value;
    }

    public void down(double value) {
        scape.firePropertyChange("height", height, (height - value));
        height -= value;
    }

    public void acelerate(double value) {
        scape.firePropertyChange("speed", speed, (speed + value));
        speed += value;
    }

    public void decelerate(double value) {
        scape.firePropertyChange("speed", speed, (speed - value));
        speed -= value;
    }

    public void turn(double value) {
        scape.firePropertyChange("degrees", degrees, value);
        degrees = value;
    }

    public void setDirection(String route) {
        scape.firePropertyChange("direction", direction, route);
        direction = route;
    }

    public void addPredator(PropertyChangeListener predator) {
        scape.addPropertyChangeListener(predator);
    }

    public void removePredator(PropertyChangeListener predator) {
        scape.removePropertyChangeListener(predator);
    }
}
