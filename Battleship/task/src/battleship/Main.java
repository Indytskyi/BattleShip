package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        InitialField initialField = new InitialField();
        initialField.showPlayingField();
        initialField.arrangeShipOnTheGameField();
        initialField.startGame();
    }
}
