package cpsc2150.hw2;

import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * GameScreenTest.java
 *
 * @author Rafael
 * @version 1.0 9/24/2017
 */

public class GameScreenTest {
    @Test
    public void drawTest() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            boolean skip = i % 3 == 0;
            for (int j = (skip) ? 1 : 0; j < 8; j++)
                builder.append(i).append(" ").append(j).append("\n");
            if (skip)
                builder.append(i).append(" ").append(0).append("\n");
        }
        builder.append('n');
        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        System.setIn(in);
        GameScreen.main(new String[0]);
    }

}