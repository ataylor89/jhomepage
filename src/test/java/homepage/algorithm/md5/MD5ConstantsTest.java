package homepage.algorithm.md5;

import java.math.BigDecimal;
import java.util.List;
import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MD5ConstantsTest {

    private static List<String> constants;

    @BeforeAll
    public static void setUpClass() throws Exception {
        URI uri = MD5ConstantsTest.class.getClassLoader().getResource("md5_constants.txt").toURI();
        Path path = Paths.get(uri);
        constants = Files.readAllLines(path);
    }

    @Test
    public void testConstants() {
        int[] table = new int[64];
        for (int i = 0; i < 64; i++) {
            table[i] = new BigDecimal(Math.abs(Math.sin(i+1))).multiply(new BigDecimal("4294967296")).intValue();
            assertEquals(Integer.toUnsignedString(table[i]), constants.get(i));
        }
    }

}
