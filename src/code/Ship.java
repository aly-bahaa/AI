package code;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ship {

    private boolean isWrecked;
    private int BBdamage;

    private Point location;

    private int passengers;

    private boolean isBBretrievable;


    public Ship(boolean isWrecked, int BBdamage, Point location, int passengers, boolean isBBretrievable) {
        this.isWrecked = isWrecked;
        this.BBdamage = BBdamage;
        this.location = location;
        this.passengers = passengers;
        this.isBBretrievable = isBBretrievable;
    }

    public boolean isWrecked() {
        return isWrecked;
    }

    public void setWrecked(boolean wrecked) {
        isWrecked = wrecked;
    }

    public int getBBdamage() {
        return BBdamage;
    }

    public void setBBdamage(int BBdamage) {
        if (BBdamage < 0) BBdamage = 0;
        this.BBdamage = BBdamage;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        if (passengers < 0) passengers = 0;
        this.passengers = passengers;
    }

    public boolean isBBretrievable() {
        return isBBretrievable;
    }

    public void setBBretrievable(boolean BBretrievable) {
        isBBretrievable = BBretrievable;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "isWrecked=" + isWrecked +
                ", BBdamage=" + BBdamage +
                ", location=" + location +
                ", passengers=" + passengers +
                ", isBBretrievable=" + isBBretrievable +
                '}';
    }
}
