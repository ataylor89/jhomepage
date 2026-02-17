/*
package homepage.algorithm.md5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MD5Test {

    private MD5 md5;

    @BeforeEach
    public void setUp() {
        md5 = new MD5();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/md5_data.csv")
    public void testMD5(String input, String expected) {
        MD5Hash hash = md5.md5(input);
        String hexdigest = hash.getHexdigest();
        assertEquals(expected, hexdigest);
    }

}
*/
