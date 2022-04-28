import java.util.*;

public class MineSweeperUtils {

    public int getNumMines(int numColumns, int numRows) {
        int gameBoardSize = getNumberOfSquares(numColumns, numRows);
        int numMines = gameBoardSize / 4;
        return numMines;
    }

    public int getRandCoord(int i) {
        Random rand = new Random();
        int coord = 0;
        coord = rand.nextInt(i);
        return coord;

    }

    public List addToList(String sb) {
        List<String> lst = new ArrayList<String>();
        lst.add(sb);
        return lst;
    }

    public int[] countSurroundingSquares(LinkedHashMap gameBoardLayoutMap, int key, int rowStringLength) {

        if (key <= rowStringLength) {
            if (((key - 1) % rowStringLength) == 0) {

                int[] surrounding = { 2, (1 + rowStringLength), (2 + rowStringLength) };
                return surrounding;
            } else if (key == rowStringLength) {
                int[] surrounding = { (key - 1), (key + rowStringLength), (key + rowStringLength - 1) };
                return surrounding;
            } else {
                int[] surrounding = { (key - 1), (key + 1), (key + rowStringLength), (key + rowStringLength + 1),
                        (key + rowStringLength - 1) };
                return surrounding;
            }
        } else if (key > (gameBoardLayoutMap.size() - rowStringLength)) {
            if ((key - 1) % rowStringLength == 0) {
                int[] surrounding = { (key + 1), (key - rowStringLength + 1), (key - rowStringLength) };
                return surrounding;
            } else if (key == gameBoardLayoutMap.size()) {
                int[] surrounding = { (key - 1), (key - rowStringLength - 1), (key - rowStringLength) };
                return surrounding;
            } else {
                int[] surrounding = { (key - 1), (key + 1), (key - rowStringLength), (key - rowStringLength + 1),
                        (key - rowStringLength - 1) };
                return surrounding;
            }
        } else {
            if (((key - 1) % rowStringLength) == 0) {
                int[] surrounding = { (key - rowStringLength), (key - rowStringLength + 1), (key + 1),
                        (key + rowStringLength),
                        (key + rowStringLength + 1) };
                return surrounding;
            }
            if (key % rowStringLength == 0) {
                int[] surrounding = { (key - 1), (key - rowStringLength - 1), (key - rowStringLength),
                        (key + rowStringLength),
                        (key + rowStringLength - 1) };
                return surrounding;
            } else {
                int[] surrounding = { (key - 1), (key + 1), (key - rowStringLength - 1), (key - rowStringLength),
                        (key - rowStringLength + 1),
                        (key + rowStringLength + 1), (key + rowStringLength), (key + rowStringLength - 1) };
                return surrounding;
            }
        }
    }

    public String returnChar(String squareChar) {
        int squareCharNextInt = Integer.valueOf(squareChar);
        switch (squareCharNextInt) {
            case 0:
                squareChar = "1";
                break;
            case 1:
                squareChar = "2";
                break;
            case 2:
                squareChar = "3";
                break;
            case 3:
                squareChar = "4";
                break;
            case 4:
                squareChar = "5";
                break;
            case 5:
                squareChar = "6";
                break;
            case 6:
                squareChar = "7";
                break;
            case 7:
                squareChar = "8";
                break;
            default:
                squareChar = squareChar;
                break;
        }
        return squareChar;
    }

    public int getNumberOfSquares(int columns, int rows) {
        int totalSquares = columns * rows;
        return totalSquares;
    }

}
