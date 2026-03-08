package homepage.algorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Rot88Test {

    private Rot88 rot88;
    private Path testData;

    @BeforeEach
    public void setUp() {
        rot88 = new Rot88();
        Path projectRoot = Paths.get(".").normalize().toAbsolutePath();
        testData = Paths.get(projectRoot.toString(), "src", "test", "resources", "rot88");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/rot88/rot88_data.csv")
    public void testRot88(String inputFile, String expectedFile) {
        try {
            Path inputPath = Paths.get(testData.toString(), inputFile);
            Path expectedPath = Paths.get(testData.toString(), expectedFile);
            String input = Files.readString(inputPath);
            String expected = Files.readString(expectedPath);
            String ciphertext = rot88.rot88(input);
            assertEquals(ciphertext, expected);
            String plaintext = rot88.rot88(ciphertext);
            assertEquals(plaintext, input);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
