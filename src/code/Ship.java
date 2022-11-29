package code;

import java.util.ArrayList;
import java.util.Random;

public class Ship {

    private boolean isWrecked;
    private int health;
    private int x;
    private int y;
    private int passengers;

    public Ship(int x, int y) {
        isWrecked = false;
        health = 20;
        this.x = x;
        this.y = y;
        Random r = new Random();
        passengers = r.nextInt(100);
    }
}
