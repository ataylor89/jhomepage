package homepage.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Rot13Test {

    private Rot13 rot13;

    @BeforeEach
    public void setUp() {
        rot13 = new Rot13();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/rot13_data.csv")
    public void testRot13(String input, String expected) {
        String ciphertext = rot13.rot13(input);
        assertEquals(ciphertext, expected);
        String plaintext = rot13.rot13(ciphertext);
        assertEquals(plaintext, input);
    }

}
