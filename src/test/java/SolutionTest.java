import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    String field = "stwswtpptpttpwpp";
    String human = "human";
    String swamper = "swamper";
    String woodman = "woodman";

    @Test
    public void getResultHuman() {
        int expected = 10;
        int actual = Solution.getResult(field, human);
        assertEquals("Human test", expected, actual);
    }

    @Test
    public void getResultSwamper() {
        int expected = 15;
        int actual = Solution.getResult(field, swamper);
        assertEquals("swamper test", expected, actual);
    }

    @Test
    public void getResultWoodman() {
        int expected = 12;
        int actual = Solution.getResult(field, woodman);
        assertEquals("woodman test", expected, actual);
    }
}
