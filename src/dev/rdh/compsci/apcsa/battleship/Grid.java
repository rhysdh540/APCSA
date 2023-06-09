package dev.rdh.compsci.apcsa.battleship;

import java.util.List;

public class Grid {
    private Location[][] grid;
    private List<Ship> ships;
    public static final int NUM_ROWS = 10, NUM_COLS = 10;

    public Grid(){
        grid = new Location[NUM_ROWS][NUM_COLS];
        for(Location[] row : grid)
            for(int i = 0; i < row.length; i++)
                row[i] = new Location();
    }

    public void markHit(int row, int col) {
        grid[col][row].markHit();
    }

    public void markMiss(int row, int col) {
        grid[col][row].markMiss();
    }

    public void setStatus(int row, int col, int status) {
        grid[col][row].setStatus(status);
    }

    public int getStatus(int row, int col){
        return grid[col][row].getStatus();
    }

    public boolean alreadyGuessed(int row, int col){
        return !grid[col][row].isUnguessed();
    }

    public void setShip(int row, int col, boolean ship){
        grid[col][row].setShip(ship);
    }

    public boolean hasShip(int row, int col){
        return grid[col][row].hasShip();
    }

    public Location get(int row, int col){
        return grid[col][row];
    }

    public int numRows(){
        return grid.length;
    }

    public int numCols(){
        return grid[0].length;
    }

    public void printStatus() {
        System.out.print(" ");
        for(int i = 1; i <= NUM_COLS; i++)
            System.out.print(" " + i);
        System.out.println();
        for(int row = 0; row < NUM_ROWS; row++){
            System.out.print((char)('A' + row));
            for(int col = 0; col < NUM_COLS; col++){
                if(grid[row][col].checkHit())
                    System.out.print(" X");
                else if(grid[row][col].checkMiss())
                    System.out.print(" O");
                else
                    System.out.print(" -");
            }
            System.out.println(' ');
        }
    }

    public void printShips(){
        System.out.print(" ");
        for(int i = 1; i <= NUM_COLS; i++)
            System.out.print(" " + i);
        System.out.println();
        for(int row = 0; row < NUM_ROWS; row++){
            System.out.print((char)(row + 'A'));
            for(int col = 0; col < NUM_COLS; col++){
                System.out.print(' ');
                System.out.print(grid[row][col].hasShip() ? "X" : "-");
            }
            System.out.println(' ');
        }
    }
    public void addShip(Ship ship) {
        if(ship == null || ship.getDirection() == -1)
            return;
        for(int i = 0; i < ship.getLength(); i++)
            if(ship.getDirection() == 0)
                grid[ship.getRow()][ship.getCol() + i].setShip(true);
            else
                grid[ship.getRow() + i][ship.getCol()].setShip(true);
    }
    public Location[][] grid(){
        return grid;
    }
}