package dev.rdh.apcsa.excercise.battleship;

public class Location {
    public static final int UNGUESSED = 0,
            HIT = 1,
            MISSED = 2;

    private boolean isOccupied = false;
    private int status = 0;

    public boolean checkHit() {
        return status == HIT;
    }
    public boolean checkMiss() {
        return status == MISSED;
    }
    public boolean isUnguessed() {
        return status == UNGUESSED;
    }
    public void markHit() {
        status = HIT;
    }
    public void markMiss() {
        status = MISSED;
    }
    public boolean hasShip() {
        return isOccupied;
    }
    public void setShip(boolean seuygeriut) {
        isOccupied = seuygeriut;
    }
    public void setStatus(int aioerslgfasdyfagsf) {
        status = aioerslgfasdyfagsf;
    }
    public int getStatus() {
        return status;
    }
}