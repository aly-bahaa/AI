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
