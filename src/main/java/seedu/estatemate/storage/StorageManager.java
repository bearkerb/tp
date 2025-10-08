package seedu.estatemate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.estatemate.commons.core.LogsCenter;
import seedu.estatemate.commons.exceptions.DataLoadingException;
import seedu.estatemate.model.ReadOnlyEstateMate;
import seedu.estatemate.model.ReadOnlyUserPrefs;
import seedu.estatemate.model.UserPrefs;

/**
 * Manages storage of EstateMate data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EstateMateStorage estateMateStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EstateMateStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EstateMateStorage estateMateStorage, UserPrefsStorage userPrefsStorage) {
        this.estateMateStorage = estateMateStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ EstateMate methods ==============================

    @Override
    public Path getEstateMateFilePath() {
        return estateMateStorage.getEstateMateFilePath();
    }

    @Override
    public Optional<ReadOnlyEstateMate> readEstateMate() throws DataLoadingException {
        return readEstateMate(estateMateStorage.getEstateMateFilePath());
    }

    @Override
    public Optional<ReadOnlyEstateMate> readEstateMate(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return estateMateStorage.readEstateMate(filePath);
    }

    @Override
    public void saveEstateMate(ReadOnlyEstateMate estateMate) throws IOException {
        saveEstateMate(estateMate, estateMateStorage.getEstateMateFilePath());
    }

    @Override
    public void saveEstateMate(ReadOnlyEstateMate estateMate, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        estateMateStorage.saveEstateMate(estateMate, filePath);
    }

}
