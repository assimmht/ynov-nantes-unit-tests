import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MineSweeperTDD {
    MineSweeperUtils utils = new MineSweeperUtils();
    private int[][] dimensions = { { 2, 2 }, { 4, 4 }, { 10, 10 } };
    List initialGridLayout = new ArrayList();
    List populatedGridLayout = new ArrayList();
    LinkedHashMap<Integer, String> gameBoardLayoutMap = new LinkedHashMap<Integer, String>();

    public static void main(String[] args) {
        MineSweeperTDD mineSweeper = new MineSweeperTDD();
    }

    public MineSweeperTDD() {
        int[] individualGameDimensions = new int[2];
        for (int i = 0; i < dimensions.length; i++) {
            System.out.println("Field #" + (i + 1));
            individualGameDimensions[0] = dimensions[i][0];
            individualGameDimensions[1] = dimensions[i][1];
            createGridLayout(individualGameDimensions);
            addRandomMines(initialGridLayout);
            updateSurrounding(initialGridLayout);
            printOutput(gameBoardLayoutMap, dimensions[i][1]);
        }
    }

    public List createGridLayout(int[] individualGameDimensions) {
        StringBuilder b = new StringBuilder();
        for (int j = 0; j < individualGameDimensions[1]; j++) {
            for (int i = 0; i < individualGameDimensions[0]; i++) {
                b.append("0");
            }
            initialGridLayout.add(j, utils.addToList(b.toString()));
            b.delete(0, b.length());
        }
        return initialGridLayout;
    }

    public List addRandomMines(List initialGridLayout) {
        int genRandomRowNumber = 0;
        String getRandRowContent;
        String rowMinusBrackets;
        StringBuffer mineString = new StringBuffer();
        int newRowLength;
        int getMinePosition;
        int numMines = utils.getNumMines(initialGridLayout.size(), (initialGridLayout.get(0).toString().length() - 2));

        for (int i = 0; i < numMines; i++) {
            genRandomRowNumber = utils.getRandCoord(initialGridLayout.size());
            getRandRowContent = initialGridLayout.get(genRandomRowNumber).toString();
            rowMinusBrackets = getRandRowContent.substring(1, (getRandRowContent.length() - 1));
            newRowLength = rowMinusBrackets.length();
            getMinePosition = utils.getRandCoord(newRowLength);
            for (int rowPosition = 1; rowPosition <= rowMinusBrackets.length(); rowPosition++) {
                if (rowPosition < (getMinePosition + 1)) {
                    mineString.append(rowMinusBrackets.charAt(rowPosition - 1));
                } else if (rowPosition == (getMinePosition + 1)) {
                    if ((rowMinusBrackets.substring((rowPosition - 1), (rowPosition)).equals("*"))) {
                        i--;
                        mineString.append("*");
                    } else {
                        mineString.append("*");
                    }
                } else {
                    mineString.append(rowMinusBrackets.charAt(rowPosition - 1));
                }
            }
            mineString.insert(0, "[");
            mineString.append("]");
            initialGridLayout.set(genRandomRowNumber, mineString.toString());
            mineString.delete(0, mineString.length());
        }
        return initialGridLayout;
    }

    public LinkedHashMap updateSurrounding(List initialGridLayout) {

        String rowMinusBrackets;
        String rowToString;
        rowToString = initialGridLayout.get(0).toString();
        int rowStringLength;
        rowStringLength = rowToString.length() - 2;
        int keyPlaceHolder = 0;

        for (int rowNumber = 0; rowNumber < initialGridLayout.size(); rowNumber++) {
            rowToString = initialGridLayout.get(rowNumber).toString();
            rowMinusBrackets = rowToString.substring(1, (rowToString.length() - 1));
            rowStringLength = rowMinusBrackets.length();
            for (int rowPosition = 0; rowPosition < rowStringLength; rowPosition++) {
                keyPlaceHolder++;
                gameBoardLayoutMap.put((keyPlaceHolder), String.valueOf(rowMinusBrackets.charAt(rowPosition)));
            }
        }
        String mapSquareCharacter;
        int key = 0;
        for (Map.Entry<Integer, String> square : gameBoardLayoutMap.entrySet()) {
            key = square.getKey();
            mapSquareCharacter = square.getValue();
            if (mapSquareCharacter.equals("*")) {
                int[] numberOfSurrounding = utils.countSurroundingSquares(gameBoardLayoutMap, key, rowStringLength);
                for (int keyNumber = 0; keyNumber < numberOfSurrounding.length; keyNumber++) {
                    int keyValue = numberOfSurrounding[keyNumber];
                    String mapEntryAtKey = gameBoardLayoutMap.get(keyValue);
                    if (!(mapEntryAtKey.equals("*"))) {
                        gameBoardLayoutMap.put(numberOfSurrounding[keyNumber], utils.returnChar(mapEntryAtKey));
                    }
                }
            }
        }
        initialGridLayout.clear();
        return gameBoardLayoutMap;
    }

    public void printOutput(LinkedHashMap gameBoardLayoutMap, int numRowCharacters) {

        StringBuilder outputLine = new StringBuilder();
        int rowPositionCount = 1;
        for (int i = 1; i <= gameBoardLayoutMap.size(); i++) {
            if (rowPositionCount <= numRowCharacters) {
                outputLine.append(gameBoardLayoutMap.get(i));
                rowPositionCount++;
            } else {
                System.out.println(outputLine.toString());
                rowPositionCount = 1;
                i--;
                outputLine.delete(0, outputLine.length());
            }
        }
        System.out.println(outputLine.toString());
        outputLine.delete(0, outputLine.length());
        System.out.println("\n");
        gameBoardLayoutMap.clear();
    }

    public int[][] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[][] dimensions) {
        this.dimensions = dimensions;
    }

    public List getGridLayout() {
        return initialGridLayout;
    }

    public void setGridLayout(List initialGridLayout) {
        this.initialGridLayout = initialGridLayout;
    }

    public List getPopulatedGridLayout() {
        return populatedGridLayout;
    }

    public void setPopulatedGridLayout(List populatedGridLayout) {
        this.populatedGridLayout = populatedGridLayout;
    }

}
