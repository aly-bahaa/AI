package code;

import java.awt.*;
import java.util.ArrayList;

public class State {
    private int cgX;
    private int cgY;
    private int cgC;
    private int NdeadPeople;
    private int NpassengersOnCg;//hazawedo kol ma pickup w a2alelo kol ma drop
    private ArrayList<Ship> ships;
    private ArrayList<Point> stations;
    private int NblackBoxesRetrieved;
    private int totalPeople;

    public int getNblackBoxesRetrieved() {
        return NblackBoxesRetrieved;
    }

    public void setNblackBoxesRetrieved(int nblackBoxesRetrieved) {
        NblackBoxesRetrieved = nblackBoxesRetrieved;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        if (totalPeople < 0) totalPeople = 0;
        this.totalPeople = totalPeople;
    }

    public State(int cgX, int cgY, int cgC, ArrayList<Ship> ships, ArrayList<Point> stations,
                 int NpassengersOnCg, int NblackBoxesRetrieved, int NdeadPeople, int totalPeople) {
        this.cgX = cgX;
        this.cgY = cgY;
        this.cgC = cgC;
        this.ships = ships;
        this.stations = stations;
        this.NpassengersOnCg = NpassengersOnCg;
        this.NblackBoxesRetrieved = NblackBoxesRetrieved;
        this.NdeadPeople = NdeadPeople;
        this.totalPeople = totalPeople;
    }

    @Override
    public String toString() {
        return "State{" +
                "cgX=" + cgX +
                ", cgY=" + cgY +
                ", cgC=" + cgC +
                ", totalPPl=" + totalPeople +
                ", NdeadPeople=" + NdeadPeople +
                ", NpassengersOnCg=" + NpassengersOnCg +
                ", NblackBoxesRetrieved=" + NblackBoxesRetrieved +
                ", ships=" + ships +
                ", stations=" + stations +
                '}';
    }

    public int getCgX() {
        return cgX;
    }

    public void setCgX(int cgX) {
        this.cgX = cgX;
    }

    public int getCgY() {
        return cgY;
    }

    public void setCgY(int cgY) {
        this.cgY = cgY;
    }

    public int getCgC() {
        return cgC;
    }

    public void setCgC(int cgC) {
        this.cgC = cgC;
    }

    public int getNdeadPeople() {
        return NdeadPeople;
    }

    public void setNdeadPeople(int ndeadPeople) {

        NdeadPeople = ndeadPeople;
    }


    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }
    public ArrayList<Point> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Point> stations) {
        this.stations = stations;
    }
    public int getNpassengersOnCg() {
        return NpassengersOnCg;
    }

    public void setNpassengersOnCg(int npassengersOnCg) {
        NpassengersOnCg = npassengersOnCg;
    }
}
