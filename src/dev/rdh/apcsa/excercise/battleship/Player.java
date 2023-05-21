package dev.rdh.apcsa.excercise.battleship;

public class Player {
    private Grid grid, otherGrid;

    public Player() {
        grid = new Grid();
        otherGrid = new Grid();
    }
    private int i = 0;
    public void chooseShipLocation(Ship s, int row, int col, int direction){
        if(i==5)
            return;
        s.setDirection(direction);
        s.setLocation(col, row);
        grid.addShip(s);
        i++;
    }
    public void printMyShips() {
        grid.printShips();
    }
    public void printOpponentGuesses() {
        grid.printStatus();
    }
    public void printMyGuesses() {
        otherGrid.printStatus();
    }
    public int recordOpponentGuess(int row, int col){
        if(grid.alreadyGuessed(row, col))
            return -1;
        if(grid.hasShip(row, col)) {
            grid.markHit(row, col);
            return 1;
        }
        else {
            grid.markMiss(row, col);
            return 0;
        }
    }
    public Grid getMyGrid() {
        return grid;
    }
    public Grid getOtherGrid() {
        return otherGrid;
    }
    public boolean allSunk() {
        int sum = 0;
        for(Location[] l : grid.grid())
            for(Location loc : l)
                if(loc.checkHit())
                    sum++;
        return sum >= 17;
    }
}