package code;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private State state;
    private ArrayList<String> possibleActions;

    private Node parent;
    private int depth;
    private String operator;
    private String pathCost;
    private int m;
    private int n;



    public Node (State state,int m, int n,Node parent, int depth,String operator ,String pathCost){
        this.state = state;
        this.m = m;
        this.n = n;
        this.parent = parent;
        this.depth = depth;
        this.operator = operator;
        this.pathCost = pathCost;
        this.possibleActions = checkPossibleActions();
    }


    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                "# possibleActions=" + possibleActions +
                "# depth=" + depth +
                "# operator='" + operator + '\'' +
                "# pathCost='" + pathCost + '\'' +
                "# parent=" + parent +
                "# m=" + m +
                "# n=" + n +
                '}';
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPathCost() {
        return pathCost;
    }

    public void setPathCost(String pathCost) {
        this.pathCost = pathCost;
    }

    private ArrayList<String> checkPossibleActions(){
        ArrayList<String> pa = new ArrayList<>();
        int  agentX = this.getState().getCgX();
        int agentY = state.getCgY();
        Point agentLoc = new Point(agentX,agentY);
       if (agentX!=0) pa.add("up");
       if (agentY!=0) pa.add("left");
       if (agentX!= n - 1) pa.add("down");
       if (agentY!= m -1) pa.add("right");
       for (int i = 0;i<state.getShips().size();i++){
          // System.out.println( agentLoc +",  "+ state.getShips().get(i).getLocation());
           if ( state.getShips().get(i).getLocation().equals(agentLoc) ) {pa.add("pickUp");}
           if (state.getShips().get(i).getLocation().equals(agentLoc) && state.getShips().get(i).isWrecked() ) {
               if (state.getShips().get(i).isBBretrievable()) pa.add("retrieve");
           }
       }
        for (int i = 0;i<state.getStations().size();i++) {
            if (state.getStations().get(i).getLocation().equals(agentLoc)) pa.add("drop");
        }
       // System.out.println("m:" + m + "n: " + this.getN() + "x: "+agentX + "y: " + agentY);
        return pa;
    }


    public void pickUp(State state){
      int currentCgC = state.getCgC();
      Point agentLoc = new Point(state.getCgX(),state.getCgY());
        if (state.getTotalPeople() > 0)   state.setNdeadPeople(state.getNdeadPeople() + 1);
        for (Ship s: state.getShips()) {
            if (s.getPassengers() > 0)
            s.setPassengers(s.getPassengers() - 1);
            state.setTotalPeople(state.getTotalPeople() - 1);
            if (s.isBBretrievable()) s.setBBdamage(s.getBBdamage() + 1);
            if (s.getLocation().equals(agentLoc) && s.getPassengers() > 0) {//el ship el ana 3aleha
                if (currentCgC >= s.getPassengers()){
                   // state.setCgC(state.getCgC() - s.getPassengers());
                    state.setNpassengersOnCg(s.getPassengers());
                    s.setPassengers(0);
                    state.setTotalPeople(state.getTotalPeople() - state.getNpassengersOnCg());
                    if (s.getPassengers() == 0) s.setWrecked(true);
                    if (s.isWrecked() && s.getBBdamage() < 20)  s.setBBretrievable(true);
                    getPossibleActions().add("retrieve");
                }else{
                    state.setNpassengersOnCg(s.getPassengers());
                    s.setPassengers(s.getPassengers() - currentCgC);
                    state.setTotalPeople(state.getTotalPeople() - state.getNpassengersOnCg());
                    if (s.getPassengers() == 0) s.setWrecked(true);
                    if (s.isWrecked() && s.getBBdamage() < 20)  s.setBBretrievable(true);
                  //  state.setCgC(0);
                }
            }
        }
    }
    public void retrieve(State state){
        Point agentLoc = new Point(state.getCgX(),state.getCgY());
        if (state.getTotalPeople() > 0)   state.setNdeadPeople(state.getNdeadPeople() + 1);
        for (Ship s : state.getShips()) {
            s.setPassengers(s.getPassengers() - 1);
            state.setTotalPeople(state.getTotalPeople() - 1);
            if (s.isBBretrievable()) s.setBBdamage(s.getBBdamage() + 1);
            if (s.getLocation().equals(agentLoc)) {
                if (s.isBBretrievable()) {
                    state.setNblackBoxesRetrieved(state.getNblackBoxesRetrieved() + 1);
                    s.setBBretrievable(false);
                }
            }
        }
    }
    public void drop(State state){
        int currentCgC = state.getCgC();
        if (state.getTotalPeople() > 0)   state.setNdeadPeople(state.getNdeadPeople() + 1);
        Point agentLoc = new Point(state.getCgX(),state.getCgY());
        for (Point p: state.getStations()) {
            if (p.equals(agentLoc)) {
                state.setNpassengersOnCg(0);
            }
        }
    }
    public void move(String direction,State state){
        if (state.getTotalPeople() > 0)   state.setNdeadPeople(state.getNdeadPeople() + 1);
        for (Ship s: state.getShips()) {
            s.setPassengers(s.getPassengers() - 1);
            state.setTotalPeople(state.getTotalPeople() - 1);
            if (s.isBBretrievable()) s.setBBdamage(s.getBBdamage() + 1);
        }
     if(direction == "up"){
        state.setCgX( state.getCgX() - 1);
     } else if (direction == "down") {
         state.setCgX(state.getCgX() + 1);
     } else if (direction == "right") {
         state.setCgY( state.getCgY() + 1);
     } else if (direction == "left") {
         state.setCgY( state.getCgY() - 1);
     }
        this.possibleActions = checkPossibleActions();
    // return state;
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
