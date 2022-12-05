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

    public Ship(Point p, int passengers,boolean isWrecked) {
        this.isWrecked = isWrecked;
        this.passengers = passengers;
        if (isWrecked)  this.isBBretrievable = true;
        this.location = p;
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
