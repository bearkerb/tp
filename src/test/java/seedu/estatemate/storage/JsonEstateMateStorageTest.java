package seedu.estatemate.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.estatemate.testutil.Assert.assertThrows;
import static seedu.estatemate.testutil.TypicalPersons.ALICE;
import static seedu.estatemate.testutil.TypicalPersons.HOON;
import static seedu.estatemate.testutil.TypicalPersons.IDA;
import static seedu.estatemate.testutil.TypicalPersons.getTypicalEstateMate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.estatemate.commons.exceptions.DataLoadingException;
import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.ReadOnlyEstateMate;

public class JsonEstateMateStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEstateMateStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEstateMate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEstateMate(null));
    }

    private java.util.Optional<ReadOnlyEstateMate> readEstateMate(String filePath) throws Exception {
        return new JsonEstateMateStorage(Paths.get(filePath)).readEstateMate(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEstateMate("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readEstateMate("notJsonFormatEstateMate.json"));
    }

    @Test
    public void readEstateMate_invalidPersonEstateMate_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readEstateMate("invalidPersonEstateMate.json"));
    }

    @Test
    public void readEstateMate_invalidAndValidPersonEstateMate_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readEstateMate("invalidAndValidPersonEstateMate.json"));
    }

    @Test
    public void readAndSaveEstateMate_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEstateMate.json");
        EstateMate original = getTypicalEstateMate();
        JsonEstateMateStorage jsonEstateMateStorage = new JsonEstateMateStorage(filePath);

        // Save in new file and read back
        jsonEstateMateStorage.saveEstateMate(original, filePath);
        ReadOnlyEstateMate readBack = jsonEstateMateStorage.readEstateMate(filePath).get();
        assertEquals(original, new EstateMate(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonEstateMateStorage.saveEstateMate(original, filePath);
        readBack = jsonEstateMateStorage.readEstateMate(filePath).get();
        assertEquals(original, new EstateMate(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonEstateMateStorage.saveEstateMate(original); // file path not specified
        readBack = jsonEstateMateStorage.readEstateMate().get(); // file path not specified
        assertEquals(original, new EstateMate(readBack));

    }

    @Test
    public void saveEstateMate_nullEstateMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEstateMate(null, "SomeFile.json"));
    }

    /**
     * Saves {@code estateMate} at the specified {@code filePath}.
     */
    private void saveEstateMate(ReadOnlyEstateMate estateMate, String filePath) {
        try {
            new JsonEstateMateStorage(Paths.get(filePath))
                    .saveEstateMate(estateMate, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEstateMate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEstateMate(new EstateMate(), null));
    }
}
