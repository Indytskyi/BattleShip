package battleship.test;

public enum Ships {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5, new int[][] {}),
    BATTLESHIP("Battleship", 4, new int[][] {}),
    SUBMARINE("Submarine", 3, new int[][] {}),
    CRUISER("Cruiser", 3, new int[][] {}),
    DESTROYER("Destroyer", 2, new int[][] {});



    private final String TYPE_OF_SHIP;
    private final int SIZE;
    private int[][] shipCoordinates;

    private boolean validArrangementShip;

    Ships(String TYPE_OF_SHIP, int SIZE, int[][] shipCoordinates) {
        this.TYPE_OF_SHIP = TYPE_OF_SHIP;
        this.SIZE = SIZE;
        this.shipCoordinates = shipCoordinates;
    }

    public void setShipCoordinates(String startShipPosition, String finishShipPosition) {
        int columnStartCheck = Integer.parseInt(startShipPosition.substring(1));
        int columnFinishCheck =  Integer.parseInt(finishShipPosition.substring(1));
        int rowStartCheck = startShipPosition.charAt(0) - 'A' + 1 ;
        int rowFinishCheck = finishShipPosition.charAt(0) - 'A' + 1;


        if (rowStartCheck == rowFinishCheck || columnFinishCheck == columnStartCheck) {
            validArrangementShip = true;
        } else {
            validArrangementShip = false;
        }

        shipCoordinates = new int[2][Math.abs((rowStartCheck + columnStartCheck) -
                (columnFinishCheck + rowFinishCheck)) + 1];

        for (int i = 0; i < shipCoordinates[0].length; i++) {
           if (columnStartCheck == columnFinishCheck) {
               shipCoordinates[1][i] = columnStartCheck;
           } else {
               shipCoordinates[1][i] = Math.min(columnStartCheck, columnFinishCheck) + i;
           }

           if (rowStartCheck == rowFinishCheck) {
               shipCoordinates[0][i] = rowStartCheck;
           } else {
               shipCoordinates[0][i] = Math.min(rowStartCheck, rowFinishCheck) + i;
           }
        }
    }



    public String getTypeOfShip() {
        return TYPE_OF_SHIP;
    }

    public int getSize() {
        return SIZE;
    }

    public int[][] getShipCoordinates() {
        return shipCoordinates;
    }

    public boolean isValidArrangementShip() {
        return validArrangementShip;
    }
}
