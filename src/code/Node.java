package code;

import java.util.ArrayList;

public class Node {
    private String state;
    private int agentX;
    private int agentY;
    private int agentCapacity;
    private ArrayList<String> possibleActions;

    public Node(String state){
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
