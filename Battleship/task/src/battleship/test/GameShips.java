package battleship.test;

import battleship.test.PlayingField;
import battleship.test.Ships;
import battleship.test.ValidationOfField;

import java.util.Scanner;

public class GameShips {

    private final Scanner scanner = new Scanner(System.in);

    public void initializationOfShip(PlayingField playingField) {
        playingField.createBattlefield();
        playingField.printBattlefield();

        while (playingField.getCurrentShip() != Ships.values().length) {
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n",
                    playingField.getShip().getTypeOfShip(), playingField.getShip().getSize());
            while (true) {
                try {
                    String startShipPosition = scanner.next();
                    String finishShipPosition = scanner.next();
                    playingField.getShip().setShipCoordinates(startShipPosition, finishShipPosition);
                    if (!ValidationOfField.CheckPlaceOfShip(playingField, startShipPosition, finishShipPosition)) {
                        continue;
                    }


                    playingField.placeShipOnBattlefield();
                    playingField.printBattlefield();
                    break;
                } catch (Exception e) {
                    System.out.println("Error! Wrong ship location! Try again:");
                }

            }
        }
    }


    public void startGame(PlayingField playingField) {
        System.out.println("\nThe game starts!");

        playingField.printBattlefieldFog();
        System.out.println("\nTake a shot!\n");

        while (playingField.getCountOfLiveShips() != 0) {
            String shot = scanner.next();
            playingField.getAttack().setCoordinates(shot);
            if (!playingField.getAttack().validateShot()) {
                continue;
            }

            playingField.setHitOfAttack();
            playingField.printBattlefieldFog();
            if (playingField.getCountOfLiveShips() != 0) {
                if (playingField.getAttack().isHitShip()) {
                    if (!playingField.getAttack().isDeadShip()) {
                        System.out.println("\nYou hit a ship! Try again:\n");
                    } else {
                        System.out.println("\nYou sank a ship! Specify a new target:\n");
                    }
                    playingField.getAttack().setDeadShip(false);
                } else {
                    System.out.println("\nYou missed!  Try again:\n");
                }
            }


        }

        System.out.println("You sank the last ship. You won. Congratulations!");
    }


}
