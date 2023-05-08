package baeldung.bufferedreadervsfilereader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileReaderUnitTest {

    @Test
    public void whenReadingAFile_thenReadsCharByChar() {
        StringBuilder result = new StringBuilder();

        try (FileReader fr = new FileReader("src/test/resources/sampleText2.txt")) {
            int i = fr.read();

            while(i != -1) {
                result.append((char)i);

                i = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("qwerty", result.toString());
    }
}
