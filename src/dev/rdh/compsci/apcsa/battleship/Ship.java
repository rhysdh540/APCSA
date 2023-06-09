package dev.rdh.compsci.apcsa.battleship;

public class Ship {
    public static final int UNSET = -1, HORIZONTAL = 0, VERTICAL = 1;

    private int row = -1, col = -1, length;
    private int dir = UNSET;

    public Ship(int length) {
        this.length = length;
    }

    public boolean isLocationSet() {
        return row != -1 || col != -1;
    }
    public boolean isDirectionSet() {
        return dir != UNSET;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getLength() {
        return length;
    }
    public int getDirection() {
        return dir;
    }
    public String toString(){
        return String.format("%s ship of length %d at (%s)", !isDirectionSet() ? "unset direction" : dir == HORIZONTAL ? "horizontal" : "vertical", length, locToString());
    }
    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }
    private String locToString(){
        return !isLocationSet() ? "unset location" : row + ", " + col;
    }
    public void setDirection(int i) {
        dir = i;
    }
}
