package battleship;

import battleship.test.GameShips;
import battleship.test.PlayingField;

public class Main {

    public static void main(String[] args) {
        PlayingField playingField = new PlayingField();
        GameShips gameShips = new GameShips();
        gameShips.initializationOfShip(playingField);
        gameShips.startGame(playingField);
    }
}
