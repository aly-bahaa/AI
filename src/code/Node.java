package code;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private State state;
    private int agentX;
    private int agentY;
    private int agentCapacity;
    private ArrayList<String> possibleActions;
    private int NremainingShips;
    private ArrayList<Ship> ships;
    private ArrayList<Point> stationLocations;
    private int m;
    private int n;



    public Node (State state,int m, int n){
        this.state = state;
        this.agentX = state.getCgX();
        this.agentY = state.getCgY();
        this.agentCapacity = state.getCgC();
        this.ships = state.getShips();
        this.stationLocations = state.getStations();
        this.m = m;
        this.n = n;
        this.possibleActions = checkPossibleActions();
    }
    public int getNremainingShips() {
        return NremainingShips;
    }

    public void setNremainingShips(int nremainingShips) {
        NremainingShips = nremainingShips;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    //public Node
//    public Node(String state) {
//        this.state = state;
//        String[] stateComponents = state.split(";");
//        String cgLocations = stateComponents[0];
//        String[] cgParsedLoc = cgLocations.split(",");
//        this.agentX = Integer.parseInt(cgParsedLoc[0]);
//        this.agentY = Integer.parseInt(cgParsedLoc[1]);
//        this.agentCapacity = Integer.parseInt(stateComponents[1]);
//        this.NremainingShips = (stateComponents[2].split(",|;").length) / 3;
//        this.shipsLocations = new ArrayList<>();
//        this.stationLocations = new ArrayList<>();
//        possibleActions = new ArrayList<>();
//        String[] shLocs = stateComponents[2].split(",");
//        for (int i =0;i<shLocs.length;i+=3){
//            int shipX = Integer.parseInt(shLocs[i]);
//            int shipY= Integer.parseInt(shLocs[i+1]);
//            int shipC = Integer.parseInt(shLocs[i+2]);
//            Ship s = new Ship(new Point(shipX,shipY),shipC,false,false,0);
//            shipsLocations.add(s);
//        }
//        String[] stLocs = stateComponents[3].split(",");
//        for (int i =0;i<stLocs.length;i+=2){
//            int stationX = Integer.parseInt(stLocs[i]);
//            int stationY= Integer.parseInt(stLocs[i+1]);
//            stationLocations.add(new Point(stationX,stationY));
//        }
////        System.out.println(stateComponents[4]);
//        possibleActions = checkPossibleActions();
//    }

    private ArrayList<String> checkPossibleActions(){
        ArrayList<String> pa = new ArrayList<>();
        Point agentLoc = new Point(agentX,agentY);
       if (agentX!=0) pa.add("up");
       if (agentY!=0) pa.add("left");
       if (agentX!= n - 1) pa.add("down");
       if (agentY!= m -1) pa.add("right");
       if (ships.contains(agentLoc)) pa.add("pickUp");
       if (stationLocations.contains(agentLoc)) pa.add("drop");
       // System.out.println("m:" + m + "n: " + this.getN() + "x: "+agentX + "y: " + agentY);
        return pa;
    }

    @Override
    public String toString() {
        return "Node{" +
                "state='" + state + '\'' +
                ", agentX=" + agentX +
                ", agentY=" + agentY +
                ", agentCapacity=" + agentCapacity +
                ", possibleActions=" + possibleActions +
                ", NremainingShips=" + NremainingShips +
                ", shipsLocations=" + ships.toString() +
                ", stationLocations=" + stationLocations.toString() +
                '}';
    }

    public void pickUp(){

    }
    public void retrieve(){

    }
    public void drop(){

    }
    public void move(String direction){
     if(direction == "up"){
         setAgentY(getAgentY()-1);
     } else if (direction == "down") {
         setAgentY(getAgentY() + 1);
     } else if (direction == "right") {
         setAgentX(getAgentX() + 1);
     } else if (direction == "left") {
         setAgentX(getAgentX() - 1);
     }
    }















    public int getAgentX() {
        return agentX;
    }

    public void setAgentX(int agentX) {
        this.agentX = agentX;
    }

    public int getAgentY() {
        return agentY;
    }

    public void setAgentY(int agnetY) {
        this.agentY = agnetY;
    }

    public int getAgentCapacity() {
        return agentCapacity;
    }

    public void setAgentCapacity(int agentCapacity) {
        this.agentCapacity = agentCapacity;
    }

    public ArrayList<String> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(ArrayList<String> possibleActions) {
        this.possibleActions = possibleActions;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }



}
