package battleship.test;

public class ValidationOfField {

    public static boolean CheckPlaceOfShip(PlayingField playingField, String startShipPosition, String finishShipPosition) throws Exception {
        int columnOfStartPosition;
        int columnOfFinishPosition;
        String rowOfStartPosition;
        String rowOfFinishPosition;
        try {

            columnOfStartPosition = Integer.parseInt(startShipPosition.substring(1));
            columnOfFinishPosition = Integer.parseInt(finishShipPosition.substring(1));


            if (columnOfFinishPosition > 10 || columnOfStartPosition < 1
            || columnOfStartPosition > 10 || columnOfFinishPosition < 1) {
                throw new NumberFormatException();
            }

            rowOfStartPosition = String.valueOf(startShipPosition.charAt(0));
            rowOfFinishPosition = String.valueOf(finishShipPosition.charAt(0));



            if (!rowOfStartPosition.matches("[A-J]") || !rowOfFinishPosition.matches("[A-J]")) {
                throw new Exception();
            }

            if (rowOfStartPosition.equals(rowOfFinishPosition)) {
                if (Math.abs(columnOfFinishPosition - columnOfStartPosition) != playingField.getShip().getSize() - 1) {
                    System.out.printf("Error! Wrong length of the %s! Try again:\n",
                            playingField.getShip().getTypeOfShip());
                    return false;
                }
            } else if (columnOfStartPosition == columnOfFinishPosition) {
                if (Math.abs(rowOfFinishPosition.charAt(0) - rowOfStartPosition.charAt(0)) != playingField.getShip().getSize() - 1) {
                    System.out.printf("Error! Wrong length of the %s! Try again:\n",
                            playingField.getShip().getTypeOfShip());
                    return false;
                }
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
                return false;
            }

            return contactWithOtherShip(playingField);
        }  catch (NumberFormatException e) {
            System.out.println("Your second part of coordinates must contains digits between (1-10)");
            return false;
        } catch (Exception e) {
            System.out.println("Your first part of coordinates must contains only one letter between (A-J)");
            return false;
        }

    }


    private static boolean contactWithOtherShip(PlayingField playingField) {
        int[][] shipCoordinates = playingField.getShip().getShipCoordinates();
        int lengthOfShip = playingField.getShip().getSize();
        int startI = shipCoordinates[0][0] -1;
        int finishJ = shipCoordinates[0][lengthOfShip - 1] + 1;
        int startJ = shipCoordinates[1][0] -1;
        int finishI = shipCoordinates[1][lengthOfShip - 1] + 1;

        for (int i = startI; i < finishI; i++) {
            for (int j = startJ; j < finishJ; j++) {
                if (playingField.getPlayingField()[i][j] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }
        }
        return true;
    }
}
