package battleship.test;

public class Attack {

    private int rowCoordinates;

    private int columnCoordinates;

    private boolean hitShip;

    private boolean deadShip;

    public void setCoordinates(String coordinates) {
        rowCoordinates = coordinates.charAt(0) - 'A' + 1;
        columnCoordinates = Integer.parseInt(coordinates.substring(1));
    }

    public int getRowCoordinates() {
        return rowCoordinates;
    }

    public int getColumnCoordinates() {
        return columnCoordinates;
    }

    public boolean isHitShip() {
        return hitShip;
    }

    public void setHitShip(boolean hitShip) {
        this.hitShip = hitShip;
    }

    public boolean validateShot() {
        if (columnCoordinates > 10 || rowCoordinates > 10) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            return false;
        }
        return true;
    }

    public boolean isDeadShip() {
        return deadShip;
    }

    public void setDeadShip(boolean deadShip) {
        this.deadShip = deadShip;
    }
}
