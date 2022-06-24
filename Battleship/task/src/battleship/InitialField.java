package battleship;

import java.util.Scanner;

public class InitialField {
    private final int SIZE_OF_FIELD = 11;
    private final String[][] playingField = new String[SIZE_OF_FIELD][SIZE_OF_FIELD];
    private final int[] ships = {5, 4, 3, 3, 2};

    private final String[] nameOfShip = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};

    private final Scanner scanner = new Scanner(System.in);

    InitialField() {
        playingField[0][0] = " ";
        char letter = 'A';
        for (int i = 1; i < SIZE_OF_FIELD; i++) {
            playingField[0][i] = String.valueOf(i);
            playingField[i][0] = String.valueOf(letter);
            letter++;
        }
        for (int i = 1; i < SIZE_OF_FIELD; i++) {
            for (int j = 1; j < SIZE_OF_FIELD; j++) {
                playingField[i][j] = "~";
            }
        }
    }

    public void showPlayingField() {
        for (String[] row : playingField) {
            for (String box : row) {
                System.out.print(box + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void arrangeShipOnTheGameField() {
        int i = 0;
        for (int sizeOfShip : ships) {

            inputShip(sizeOfShip, nameOfShip[i]);
            i++;
        }


    }

    public void startGame() {
        System.out.println("The game starts!\n");
        showPlayingField();
        int shotColumn = 0;
        String shotRow = null;
        System.out.println("Take a shot!\n");
        while (true) {
            String shot = scanner.nextLine();
            try {
                shotColumn = Integer.parseInt(shot.substring(1));
                shotRow = String.valueOf(shot.charAt(0));
                if (!shotRow.matches("[A-J]")) {
                    throw new Exception();
                }
                if (playingField[shotRow.charAt(0) - 'A' + 1][shotColumn].equals("O")) {

                    playingField[shotRow.charAt(0) - 'A' + 1][shotColumn] = "X";
                    showPlayingField();

                    System.out.println("You hit a ship!");
                } else {
                    playingField[shotRow.charAt(0) - 'A' + 1][shotColumn] = "M";
                    showPlayingField();

                    System.out.println("You missed!");
                }
                return;
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }

    }





    public void inputShip(int sizeOfShip, String nameOfShip) {
        boolean check = false;
        int columnOfStartPosition = 0;
        int columnOfFinishPosition = 0;
        String rowOfStartPosition = null;
        String rowOfFinishPosition = null;
        while (!check) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n\n", nameOfShip, sizeOfShip);

            String startShipPosition = scanner.next();
            String finishShipPosition = scanner.next();
            try {
                columnOfStartPosition = Integer.parseInt(startShipPosition.substring(1));
                columnOfFinishPosition = Integer.parseInt(finishShipPosition.substring(1));

                if (columnOfStartPosition > columnOfFinishPosition) {
                    int temp = columnOfStartPosition;
                    columnOfStartPosition = columnOfFinishPosition;
                    columnOfFinishPosition = temp;
                }




                if (columnOfFinishPosition > 10 || columnOfStartPosition < 1) {
                    throw new NumberFormatException();
                }
                rowOfStartPosition = String.valueOf(startShipPosition.charAt(0));
                rowOfFinishPosition = String.valueOf(finishShipPosition.charAt(0));

                if (rowOfStartPosition.charAt(0) > rowOfFinishPosition.charAt(0)) {
                    String temp = rowOfStartPosition;
                    rowOfStartPosition = rowOfFinishPosition;
                    rowOfFinishPosition = temp;

                }

                if (!rowOfStartPosition.matches("[A-J]") || !rowOfFinishPosition.matches("[A-J]")) {
                    throw new Exception();
                }


                if (rowOfStartPosition.equals(rowOfFinishPosition)) {
                    if (Math.abs(columnOfFinishPosition - columnOfStartPosition) != sizeOfShip - 1) {
                        System.out.println("Error! Wrong length of the Submarine! Try again:");
                        continue;
                    }
                } else if (columnOfStartPosition == columnOfFinishPosition) {
                    if (Math.abs(rowOfFinishPosition.charAt(0) - rowOfStartPosition.charAt(0)) != sizeOfShip - 1) {
                        System.out.println("Error! Wrong length of the Submarine! Try again:");
                        continue;
                    }
                } else {
                    System.out.println("Error! Wrong ship location! Try again:");
                    continue;
                }


                int columnStartCheck;
                int columnFinishCheck;

                if (columnOfStartPosition - 1 == 0) {
                    columnStartCheck = columnOfStartPosition;
                    columnFinishCheck = columnOfFinishPosition + 1;
                } else if (columnOfFinishPosition + 1 == 11) {
                    columnStartCheck = columnOfStartPosition - 1;
                    columnFinishCheck = columnOfFinishPosition;
                } else {
                    columnStartCheck = columnOfStartPosition - 1;
                    columnFinishCheck = columnOfFinishPosition + 1;
                }

                int rowStartCheck;
                int rowFinishCheck;

                if (rowOfStartPosition.charAt(0) - 'A' == 0) {
                    rowStartCheck = 1;
                    rowFinishCheck = rowOfFinishPosition.charAt(0) - 'A' + 1;
                } else if (rowOfFinishPosition.charAt(0) - 'A' == 9) {
                    rowStartCheck = rowOfStartPosition.charAt(0) - 'A' - 1;
                    rowFinishCheck = 10;
                } else {
                    rowFinishCheck = rowOfFinishPosition.charAt(0) - 'A' + 2;
                    rowStartCheck = rowOfStartPosition.charAt(0) - 'A'  ;
                }

                check = checkForAnotherShip(columnStartCheck, columnFinishCheck,
                        rowStartCheck, rowFinishCheck);

            } catch (NumberFormatException e) {
                System.out.println("Your second part of coordinates must contains digits between (1-10)");
                check = false;
            } catch (Exception e) {
                System.out.println("Your first part of coordinates must contains only one letter between (A-J)");
                check = false;
            }

        }

        for (int i = rowOfStartPosition.charAt(0) - 'A' + 1; i <= rowOfFinishPosition.charAt(0) - 'A' + 1; i++) {
            for (int j = columnOfStartPosition; j <= columnOfFinishPosition; j++) {
                playingField[i][j] = "O";
            }
        }

        showPlayingField();


    }


    private boolean checkForAnotherShip(int columnStartCheck, int columnFinishCheck,
                                        int rowStartCheck, int rowFinishCheck) {
        for (int i = rowStartCheck; i <= rowFinishCheck; i++) {
            for (int j = columnStartCheck; j <= columnFinishCheck; j++) {
                if (playingField[i][j].equals("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }
        }

        return true;
    }
}
