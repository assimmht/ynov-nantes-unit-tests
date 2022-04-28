import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class MineSweeperTDDTest {

    MineSweeperTDD mineSweeper = new MineSweeperTDD();
    int[] testDimensions = { 2, 2 };

    @Test
    public void testGetDimensions() {
        assertTrue("No Dimensions exist", mineSweeper.getDimensions().length > 0);
    }

    @Test
    public void testValidDimensions() {
        assertTrue("Row or Column values are > 100",
                ((testDimensions[0] <= 100) && (testDimensions[1] <= 100)));
        assertTrue("Row or Column values are 0",
                ((testDimensions[0] > 0) && (testDimensions[1] > 0)));
    }

    @Test
    public void testCreateGridLayout() {
        for (int i = 0; i < mineSweeper.getDimensions().length; i++) {
            String b = mineSweeper.createGridLayout(testDimensions).get(i).toString();
            int length = b.length();
            assertTrue("Initial createGridLayout is not 0 filled", b.substring(1, (length - 1)).equals("00"));
        }
    }

    @Test
    public void testAddRandomMines() {
        boolean foundMine = false;
        for (int i = 0; i < mineSweeper.gameBoardLayoutMap.size(); i++) {
            if (mineSweeper.gameBoardLayoutMap.get(i).equals("*")) {
                foundMine = true;
            }
        }
        assertTrue("No MInes found on the gameboard", foundMine == false);
    }

    @Test
    public void testUpdateSurrounding() {
        boolean foundFlags = false;
        for (int i = 0; i < mineSweeper.gameBoardLayoutMap.size(); i++) {
            if ((mineSweeper.gameBoardLayoutMap.get(i).equals("1"))
                    || (mineSweeper.gameBoardLayoutMap.get(i).equals("2"))
                    || (mineSweeper.gameBoardLayoutMap.get(i).equals("3"))) {
                foundFlags = true;
            }
        }
        assertTrue("No MInes found on the gameboard", foundFlags == false);

    }

}
