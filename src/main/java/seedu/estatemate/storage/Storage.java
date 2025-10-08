package seedu.estatemate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.estatemate.commons.exceptions.DataLoadingException;
import seedu.estatemate.model.ReadOnlyEstateMate;
import seedu.estatemate.model.ReadOnlyUserPrefs;
import seedu.estatemate.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends EstateMateStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getEstateMateFilePath();

    @Override
    Optional<ReadOnlyEstateMate> readEstateMate() throws DataLoadingException;

    @Override
    void saveEstateMate(ReadOnlyEstateMate estateMate) throws IOException;

}
