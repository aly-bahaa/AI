package code;

import java.awt.*;
import java.util.*;

public class CoastGuard extends GenericSearchProblem{

public static  int m;
public static int n;
    public static String genGrid(){

        String grid = "";
        Random r = new Random();
        int m = r.nextInt(15-5) + 5;
        int n = r.nextInt(15-5) + 5;
        int C = r.nextInt(100-30) + 30;
        int cgX = r.nextInt(m-1);
        int cgY = r.nextInt(n-1);

        return grid;
    }
    public static void printGrid(String[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    //public static int[][] createGrid(String gridString){
//        String[] input = gridString.split(";");
//        String gridSize = input[0];
//        int C = Integer.parseInt(input[1]);
//        String cgLoc = input[2];
//        String stationLoc = input[3];
//        String shipLocAndSize = input[4];
//
//        String[] parsedSizes = gridSize.split(",");
//        int m = Integer.parseInt(parsedSizes[0]);
//        int n = Integer.parseInt(parsedSizes[1]);
//
//        int[][] grid = new int[n][m];
//
//        String[] cgParsedLoc = cgLoc.split(",");
//        int cgX = Integer.parseInt(cgParsedLoc[0]);
//        int cgY = Integer.parseInt(cgParsedLoc[1]);
//        grid[cgX][cgY] = C;//placed cost guard
//
//        String[] stationParsedLoc = stationLoc.split(",");
//        for (int i=0;i<stationParsedLoc.length;i+=2){
//            int stationX = Integer.parseInt(stationParsedLoc[i]);
//            int stationY = Integer.parseInt(stationParsedLoc[i+1]);
//            grid[stationX][stationY] = 3;// placed a station
//        }
//
//        String[] shipParsedLoc = shipLocAndSize.split(",");
//        for (int i =0;i<shipParsedLoc.length;i+=3){
//            int shipX = Integer.parseInt(shipParsedLoc[i]);
//            int shipY= Integer.parseInt(shipParsedLoc[i+1]);
//            int shipCapacity = Integer.parseInt(shipParsedLoc[i+2]);
//            grid[shipX][shipY] = shipCapacity;
//        }
//
//       printGrid(grid);
//        return grid;


    public static String[][] createGrid2(String gridString){
        String[] input = gridString.split(";");
        String gridSize = input[0];
        String C = input[1];
        String cgLoc = input[2];
        String stationLoc = input[3];
        String shipLocAndSize = input[4];

        String[] parsedSizes = gridSize.split(",");
        int m = Integer.parseInt(parsedSizes[0]);
        int n = Integer.parseInt(parsedSizes[1]);

        String[][] grid = new String[n][m];

        String[] cgParsedLoc = cgLoc.split(",");
        int cgX = Integer.parseInt(cgParsedLoc[0]);
        int cgY = Integer.parseInt(cgParsedLoc[1]);
        grid[cgX][cgY] ="cg," + C;//placed cost guard

        String[] stationParsedLoc = stationLoc.split(",");
        for (int i=0;i<stationParsedLoc.length;i+=2){
            int stationX = Integer.parseInt(stationParsedLoc[i]);
            int stationY = Integer.parseInt(stationParsedLoc[i+1]);
            grid[stationX][stationY] = "st";// placed a station
        }

        String[] shipParsedLoc = shipLocAndSize.split(",");
        for (int i =0;i<shipParsedLoc.length;i+=3){
            int shipX = Integer.parseInt(shipParsedLoc[i]);
            int shipY= Integer.parseInt(shipParsedLoc[i+1]);
            int shipCapacity = Integer.parseInt(shipParsedLoc[i+2]);
            grid[shipX][shipY] ="sh,"+ shipCapacity;//placed a ship
        }

        printGrid(grid);
        return grid;

    }
    public static State extractState(String[][] grid){
        String state = "";
        //1- current loc of the cg
        int cgX = 0;
        int cgY = 0;
        int cgC = 0;
        String cg = "";
        int shC = 0;
        int totalpeople = 0;
        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Point> stations = new ArrayList<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j< grid[0].length;j++){
                //System.out.println(grid.length-1);
                if (grid[i][j] != null) {
                    if (grid[i][j].contains("cg")) {
                        cgX = i;//loc X
                        cgY = j;//loc Y
                        cg = grid[i][j];
                        String[] cgCapacity = cg.split(",");
                        cgC = Integer.parseInt(cgCapacity[1]);//its Capacity
                        //System.out.println(cgC);
                    }
                    if (grid[i][j].contains("sh")) {
                        boolean isWrecked = false;
                        String sh = grid[i][j];
                        String[] shCapacity = sh.split(",");
                        shC = Integer.parseInt(shCapacity[1]);
                        totalpeople += shC;
                        ;//its Capacity
                        if (shC == 0) isWrecked = true;
                        Ship ship = new Ship(false,0,new Point(i,j),shC,false);
                        ships.add(ship);
                      //  shipLocAndCap += i + "," + j +","+ shC + ",";
                    }
                    if (grid[i][j].contains("st")) {
                        stations.add(new Point(i,j));
                    }
                }
            }
        }
       // state =  cgX + "," + cgY + ";" + cgC + ";" + shipLocAndCap + ";" + st;
        State s = new State(cgX,cgY,cgC,ships,stations,0,0,0,totalpeople);
        return s;
    }

    public static boolean goalTest(State state){
        boolean NoshipPassengers = true;
        boolean NoBlackBoxes = true;
        boolean NoCgPassengers = true;
        boolean res = false;
        for (int i = 0;i<state.getShips().size();i++){
            if (state.getShips().get(i).getPassengers() != 0) NoshipPassengers = false;
            if (state.getShips().get(i).isBBretrievable()) NoBlackBoxes = false;
        }
        if (state.getNpassengersOnCg() != 0) NoCgPassengers = false;
        if(NoshipPassengers&NoCgPassengers&NoBlackBoxes) res = true;
      return res;
    }

    public static ArrayList<Node> expand(Node node,ArrayList<String> possibleActions,int depth) {
        ArrayList<Node> expandedNodes = new ArrayList<>();
//        HashSet<State> set = new HashSet<>();
        depth += 1;
        for (int i =0;i< possibleActions.size();i++){
            State ns = node.getState();
            ArrayList<Ship> test = new ArrayList<>();
            for (int j =0;j<ns.getShips().size();j++){
                Ship s = new Ship(ns.getShips().get(j).isWrecked(),ns.getShips().get(j).getBBdamage()
                        ,ns.getShips().get(j).getLocation(),ns.getShips().get(j).getPassengers()
                        ,ns.getShips().get(j).isBBretrievable());
                test.add(s);
            }
            State newS = new State(ns.getCgX(),ns.getCgY(),ns.getCgC(), test,ns.getStations(), ns.getNpassengersOnCg(),
                    ns.getNblackBoxesRetrieved(),ns.getNdeadPeople(),ns.getTotalPeople());
//            set.add(newS);
            String action = possibleActions.get(i);
            if (action == "up") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
                n1.move("up",newS);
                expandedNodes.add(n1);
            }
            if (action == "down") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
                n1.move("down",newS);
                //n1.move("left",n1.getState());
               // System.out.println("nDOWN:  "+n1);
                expandedNodes.add(n1);
            }
            if (action == "left") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
                n1.move("left",newS);
               // System.out.println("nLEFT:  "+n1);
                expandedNodes.add(n1);
            }
            if (action == "right") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
                n1.move("right",newS);
               // System.out.println("nRIGHT:  "+n1);
                expandedNodes.add(n1);
            }
            if (action == "pickUp") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
                n1.pickUp(newS);

               // System.out.println("nPICKUP:  "+n1);
                expandedNodes.add(n1);
            }
            if (action == "drop") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
               if (n1.getState().getNpassengersOnCg() > 0) n1.drop(newS);
                //System.out.println("nDROP:  "+n1);
                expandedNodes.add(n1);
            }
            if (action == "retrieve") {
                Node n1 = new Node(newS,m,n,node,depth,action,"1,?");
                n1.retrieve(newS);
                //System.out.println("nRETRIEVE:  "+n1);
                expandedNodes.add(n1);
            }
//            if (action == "pickUp") node.pickUp();
//            if (action == "drop") node.drop();
//            if (action == "Retrieve") node.retrieve();
         }
        return expandedNodes;
    }
    public static String getPlan(Node n){
    String[] s = n.toString().split("#");
    for (int i =0;i< s.length;i++){
        System.out.println(s[i]);
    }
       return n.toString();
    }
   public static String Solve(String gridString,String strategy, boolean visualize){
        String plan = "";
        String[][] grid = createGrid2(gridString);
        State initState = extractState(grid);
        String[] arr = gridString.split(";");
         m = Integer.parseInt(arr[0].split(",")[0]);
         n = Integer.parseInt(arr[0].split(",")[1]);
        Node initNode = new Node(initState,m,n,null,0,"","");
        int depth = 0;
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(initNode);
        int c =0;
        while (true){
            if (strategy == "bfs"){
                if (nodes.isEmpty()) return "failure";
                Node node = nodes.remove();
                if (goalTest(node.getState())){
                    int nd = node.getState().getNdeadPeople() -1;
                   // plan = getPlan(node);
                    return node.toString();
                }
               // System.out.println(node.getState().getCgX() + "," + node.getState().getCgY() + "  " + node.getOperator());
                ArrayList<Node> expandedNodes = expand(node,node.getPossibleActions(),depth);
                for (Node n: expandedNodes) {
                    nodes.add(n);
                }
                c++;
            }
            //                System.out.println("init: "+initNode);
        }
   }

    public static void main(String[] args) {
        String gridTest = "3,4;97;1,2;0,1;3,2,65;";
        String gridTest2 = "3,2;97;1,2;0,1;1,1,65;";
        String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
        String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
        String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
        String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
        String grid4 = "5,7;63;4,2;6,2,6,3;0,0,17,0,2,73,3,0,30;";
        String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
        String grid6 = "7,5;86;0,0;1,3,1,5,4,2;1,1,42,2,5,99,3,5,89;";
        String grid7= "6,7;82;1,4;2,3;1,1,58,3,0,58,4,2,72;";
        String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
        String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;";
        String grid10= "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";

       //String[][] g =  createGrid2(grid10);
       //extractState(g);
        System.out.println(Solve(gridTest,"bfs",true));
    }
}
