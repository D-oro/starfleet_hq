package org.example.common.model;

public class Ship {

    private String name;
    private String destination;
    private double speed;

    // Default constructor for Jackson
    public Ship() {
    }

    // Constructor
    public Ship(String name, String destination, double speed) {
        this.name = name;
        this.destination = destination;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public double getSpeed() {
        return speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
