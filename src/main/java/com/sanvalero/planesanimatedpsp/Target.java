package com.sanvalero.planesanimatedpsp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Creado por @ author: Pedro Orós
 * el 21/02/2021
 */
public class Target extends Thread{
    private double height;
    private double speed;
    private double degrees;
    private String direction;
    private double rango;
    private double go;

    private PropertyChangeSupport scape;

    private boolean up, down, left, right, fast, slow, on, paused, finished;

    private AppController appController;

    public Target(double height, double speed, double degrees, double rango, AppController appController) {
        this.appController = appController;
        this.height = height;
        this.speed = speed;
        this.degrees = degrees;
        this.up = false;
        this.down = false;
        this.right = false;
        this.left = false;
        this.fast = false;
        this.slow = false;
        this.on = false;
        this.paused = false;
        this.finished = false;
        this.rango = rango;

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

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public String getDirection() {
        return direction;
    }

    public void turnOn() {
        on = true;
    }

    public void pause() {
        paused = (paused) ? false:true;
    }

    public void finish() {
        finished = true;
        on = false;
    }

    public void goUp() {
        up = true;
        down = false;
        right = false;
        left = false;
    }

    public void goDown() {
        down = true;
        up = false;
        right = false;
        left = false;
    }

    public void goRight() {
        right = true;
        up = false;
        down = false;
        left = false;
    }

    public void goLeft() {
        left = true;
        up = false;
        down = false;
        right = false;
    }

    public void goFast() {
        fast = true;
    }

    public void goSlow() {
        slow = true;
    }

    public double up(double value) {
        scape.firePropertyChange("height", height, (height + value));
        height += value;
        return height;
    }

    public double down(double value) {
        scape.firePropertyChange("height", height, (height - value));
        height -= value;
        return height;
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

    @Override
    public void run() {
        while (on) {
            while(up) {
                try {
                    if(appController.azul.getLayoutY() <= 208) {
                        finished = true;
                        break;
                    }
                    if(fast) {
                        rango += 1;
                        fast = false;
                    }
                    if(slow) {
                        if(rango >= 2) {
                            rango -= 1;
                            slow = false;
                        }
                    }
                    this.down(rango);
                    go = this.down(rango);
                    appController.azul.setLayoutY(go);
                    appController.rojo.setLayoutY((appController.predator.getHeight() + 80));
                    appController.tfSpeed.setText(String.valueOf(rango + "   MPH"));
                    sleep(250);

                    while(paused) {
                        Thread.sleep(1000);
                    }

                    if(finished) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Platform.runLater(() -> appController.azul.setLayoutY(go));
            }

            while(down) {

                try {
                    if (appController.rojo.getLayoutY() >= 635) {
                        finished = true;
                        break;
                    }
                    if (fast) {
                        rango += 1;
                        fast = false;
                    }
                    if (slow) {
                        if (rango >= 2) {
                            rango -= 1;
                            slow = false;
                        }
                    }
                    this.up(rango);
                    go = this.up(rango);
                    appController.azul.setLayoutY(go);
                    appController.rojo.setLayoutY((appController.predator.getHeight() + 80));
                    appController.tfSpeed.setText(String.valueOf(rango + "   MPH"));
                    sleep(250);

                    while(paused) {
                        Thread.sleep(1000);
                    }

                    if(finished) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(right) {

                try {
                    if (appController.azul.getLayoutX() >= 775) {
                        finished = true;
                        break;
                    }
                    if (fast) {
                        rango += 1;
                        fast = false;
                    }
                    if (slow) {
                        if (rango >= 2) {
                            rango -= 1;
                            slow = false;
                        }
                    }

                    go = appController.target.getDegrees();
                    appController.azul.setLayoutX(go + (rango + 1));
                    appController.rojo.setLayoutX(go + (rango + 1));
                    appController.target.setDegrees(go + (rango + 1));
                    appController.tfSpeed.setText(String.valueOf(rango + "   MPH"));
                    sleep(250);

                    while(paused) {
                        Thread.sleep(1000);
                    }

                    if(finished) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while(left) {

                try {
                    if (appController.azul.getLayoutX() <= 40) {
                        finished = true;
                        break;
                    }
                    if (fast) {
                        rango += 1;
                        fast = false;
                    }
                    if (slow) {
                        if (rango >= 2) {
                            rango -= 1;
                            slow = false;
                        }
                    }
                    go = appController.target.getDegrees();
                    appController.azul.setLayoutX(go - (rango + 1));
                    appController.rojo.setLayoutX(go - (rango + 1));
                    appController.target.setDegrees(go - (rango + 1));
                    appController.tfSpeed.setText(String.valueOf(rango + "   MPH"));
                    sleep(250);

                    while(paused) {
                        Thread.sleep(1000);
                    }

                    if(finished) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(finished) {
                on = false;
                break;
            }
        }
        appController.gameOver();
    }
}
