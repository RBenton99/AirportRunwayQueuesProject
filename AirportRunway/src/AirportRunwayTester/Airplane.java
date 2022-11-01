/*
Josh Riddle
CS 331 - 001
Tue, Thurs 9:15 - 10:45
Fall 2022
*/

package AirportRunwayTester;

import javax.swing.ImageIcon;

public class Airplane {
    private String airplaneNumber;
    private String flightStatus;
    private int fuel;
    private boolean lowFuel;
    private boolean mechanicalIssue;
    private ImageIcon planeImage;
    
    private String imagePath = "/airplane_icon.png";

    public Airplane(String airplaneNumber, String flightStatus, int fuel,
            boolean fuelEmergency, boolean mechanicalEmergency) {
        this.airplaneNumber = airplaneNumber;
        this.flightStatus = flightStatus;
        this.fuel = fuel;
        this.lowFuel = fuelEmergency;
        this.mechanicalIssue = mechanicalEmergency;
    }
    
    // Getters
    public String getAirplaneNumber() {
        return airplaneNumber;
    }
    public String getStatus() {
        return flightStatus;
    }
    public int getFuel() {
        return fuel;
    }
    public boolean LowFuel() {
        return lowFuel;
    }
    public boolean MechanicalIssue() {
        return mechanicalIssue;
    }
    public ImageIcon getPlaneImage() {
        return planeImage;
    }
    public String getImagePath() {
        return imagePath;
    }
}
