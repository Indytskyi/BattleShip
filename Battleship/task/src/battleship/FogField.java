package battleship;

import java.util.Arrays;

public class FogField {
    private final int SIZE_OF_FIELD = 11;
    private final String[][] playingFogField = new String[SIZE_OF_FIELD][SIZE_OF_FIELD];


    public FogField() {
        playingFogField[0][0] = " ";
        char letter = 'A';
        for (int i = 1; i < SIZE_OF_FIELD; i++) {
            playingFogField[0][i] = String.valueOf(i);
            playingFogField[i][0] = String.valueOf(letter);
            letter++;
        }
        for (int i = 1; i < SIZE_OF_FIELD; i++) {
            for (int j = 1; j < SIZE_OF_FIELD; j++) {
                playingFogField[i][j] = "~";
            }
        }
    }

    public void setPlayingFogField(int row, int column, String data) {
        playingFogField[row][column] = data;
    }

    public void showPlayingField() {
        for (String[] row : playingFogField) {
            for (String box : row) {
                System.out.print(box + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
