import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ConfigTest {
    String path = "src/main/resources/config.json";

    @Test
    public void getConfigHuman() {
        HashMap<Character, Integer> expectedHuman = new HashMap<>();
        expectedHuman.put('s', 5);
        expectedHuman.put('w', 2);
        expectedHuman.put('t', 3);
        expectedHuman.put('p', 1);
        HashMap<Character, Integer> actualHuman = Config.getConfig(path, "human");
        assertEquals(expectedHuman, actualHuman);
    }

    @Test
    public void getConfigSwamper() {
        HashMap<Character, Integer> expectedSwamper = new HashMap<>();
        expectedSwamper.put('s', 2);
        expectedSwamper.put('w', 2);
        expectedSwamper.put('t', 5);
        expectedSwamper.put('p', 2);
        HashMap<Character, Integer> actualSwamper = Config.getConfig(path, "swamper");
        assertEquals(expectedSwamper, actualSwamper);
    }

    @Test
    public void getConfigWoodman() {
        HashMap<Character, Integer> expectedWoodman = new HashMap<>();
        expectedWoodman.put('s', 3);
        expectedWoodman.put('w', 3);
        expectedWoodman.put('t', 2);
        expectedWoodman.put('p', 2);
        HashMap<Character, Integer> actualWoodman = Config.getConfig(path, "woodman");
        assertEquals("woodman test", expectedWoodman, actualWoodman);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getConfig_whenWrongSpecies() {
        Config.getConfig(path, "mara");
    }
}
