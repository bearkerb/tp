package seedu.estatemate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.estatemate.commons.exceptions.DataLoadingException;
import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.ReadOnlyEstateMate;

/**
 * Represents a storage for {@link EstateMate}.
 */
public interface EstateMateStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEstateMateFilePath();

    /**
     * Returns EstateMate data as a {@link ReadOnlyEstateMate}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyEstateMate> readEstateMate() throws DataLoadingException;

    /**
     * @see #getEstateMateFilePath()
     */
    Optional<ReadOnlyEstateMate> readEstateMate(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyEstateMate} to the storage.
     * @param estateMate cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEstateMate(ReadOnlyEstateMate estateMate) throws IOException;

    /**
     * @see #saveEstateMate(ReadOnlyEstateMate)
     */
    void saveEstateMate(ReadOnlyEstateMate estateMate, Path filePath) throws IOException;

}
