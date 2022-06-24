package battleship.test;

import battleship.test.Ships;

public class PlayingField {
    private final int SIZE_OF_FIELD = 12;

    private final char[][] playingField = new char[SIZE_OF_FIELD][SIZE_OF_FIELD];

    private final Ships[] SHIPS = Ships.values();

    private int currentShip = 0;



    public void createBattlefield() {
        for (int i = 1; i < SIZE_OF_FIELD - 1; i++) {
            for (int j = 1; j < SIZE_OF_FIELD - 1; j++) {
                playingField[i][j] = '~';
            }
        }
    }

    public void printBattlefield() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 1; i < SIZE_OF_FIELD - 1; i++) {
            System.out.print((char) ('A' + i - 1) + " ");
            for (int j = 1; j < SIZE_OF_FIELD - 1; j++) {
                System.out.print(playingField[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printBattlefieldFog() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 1; i < SIZE_OF_FIELD - 1; i++) {
            System.out.print((char) ('A' + i - 1) + " ");
            for (int j = 1; j < SIZE_OF_FIELD - 1; j++) {
                System.out.print("~" + " ");
            }
            System.out.println();
        }
    }

    public void placeShipOnBattlefield() {
        for (int i = 0; i < getShip().getSize(); i++) {
            playingField[getShip().getShipCoordinates()[0][i]][getShip().getShipCoordinates()[1][i]] = 'O';
        }
        currentShip++;
    }

//
//    public void placeMissileOnBattlefield() {
//        int x = MISSILE.getShotCoordinates()[0];
//        int y = MISSILE.getShotCoordinates()[1];
//        BATTLEFIELD[x][y] = BATTLEFIELD[x][y] == 'O' ? 'X' : 'M';
//        MISSILE.setHitTheShip(BATTLEFIELD[x][y] == 'X');
//    }


    public char[][] getPlayingField() {
        return playingField;
    }

    public Ships getShip() {
        return this.SHIPS[currentShip];
    }

    public int getCurrentShip() {
        return currentShip;
    }
}
