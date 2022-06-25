package battleship.test;

import battleship.test.Ships;

public class PlayingField {
    private final int SIZE_OF_FIELD = 12;

    private final char[][] playingField = new char[SIZE_OF_FIELD][SIZE_OF_FIELD];

    private final Ships[] SHIPS = Ships.values();

    private int currentShip = 0;

    private final Attack attack = new Attack();

    private int countOfLiveShips = 5;


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
                if (playingField[i][j] == 'X') {
                    System.out.print("X ");
                } else if (playingField[i][j] == 'M') {
                    System.out.print("M ");
                } else {
                    System.out.print("~ ");
                }
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


    public void setHitOfAttack() {
        int x = attack.getRowCoordinates();
        int y = attack.getColumnCoordinates();
        if (playingField[x][y] == 'X') {
            attack.setHitShip(true);
        } else if (playingField[x][y] == 'M') {
            attack.setHitShip(false);
        } else {
            if (playingField[x][y] == 'O') {
                playingField[x][y] = 'X';
                attack.setHitShip(true);
                if (playingField[x - 1][y] != 'O' && playingField[x + 1][y] != 'O' &&
                        playingField[x][y - 1] != 'O' && playingField[x][y + 1] != 'O') {
                    if (viewTheEntireShip(x, y)) {
                        countOfLiveShips--;
                        if (countOfLiveShips != 0) {
                            attack.setDeadShip(true);
                        }
                    } else {
                        attack.setDeadShip(false);
                    }


                }
            } else {
                playingField[x][y] = 'M';
                attack.setHitShip(false);
            }
        }

    }

    public char[][] getPlayingField() {
        return playingField;
    }

    public Ships getShip() {
        return this.SHIPS[currentShip];
    }

    public int getCurrentShip() {
        return currentShip;
    }

    public Attack getAttack() {
        return attack;
    }

    public int getCountOfLiveShips() {
        return countOfLiveShips;
    }

    public void setCountOfLiveShips(int countOfLiveShips) {
        this.countOfLiveShips = countOfLiveShips;
    }

    private boolean viewTheEntireShip(int x, int y) {
        if(playingField[x + 1][y] == 'X') {
            x++;
            while (playingField[x][y] == 'X') {
                x++;
            }
        } else if (playingField[x - 1][y] == 'X') {
            x--;
            while (playingField[x][y] == 'X') {
                x--;
            }
        } else if (playingField[x][y + 1] == 'X') {
            y++;
            while (playingField[x][y] == 'X') {
                y++;
            }
        } else if (playingField[x][y - 1] == 'X') {
            y--;
            while (playingField[x][y] == 'X') {
                y--;
            }
        }
        if (playingField[x][y] != 'O') {
            return true;
        } else {
            return false;
        }

    }
}
