package uk.co.conoregan.watch2getherapi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

/**
 * Test utilities.
 */
public final class TestUtils {
    private TestUtils() {
    }

    /**
     * Reads a test file from the 'resources' folder.
     *
     * @param fileName the file name.
     * @return the file content.
     * @throws IOException if an error occurs.
     */
    public static String readTestFile(String fileName) throws IOException {
        try (InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IOException("File not found: " + fileName);
            }

            return IOUtils.toString(is, StandardCharsets.UTF_8);
        }
    }
}

