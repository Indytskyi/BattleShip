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
        System.out.println("\nThe game starts!\n");

        playingField.printBattlefieldFog();
        System.out.println("Take a shot!");


    }


}
