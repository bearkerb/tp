package seedu.estatemate.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.estatemate.commons.core.LogsCenter;
import seedu.estatemate.commons.exceptions.DataLoadingException;
import seedu.estatemate.commons.exceptions.IllegalValueException;
import seedu.estatemate.commons.util.FileUtil;
import seedu.estatemate.commons.util.JsonUtil;
import seedu.estatemate.model.ReadOnlyEstateMate;

/**
 * A class to access EstateMate data stored as a json file on the hard disk.
 */
public class JsonEstateMateStorage implements EstateMateStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEstateMateStorage.class);

    private Path filePath;

    public JsonEstateMateStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEstateMateFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEstateMate> readEstateMate() throws DataLoadingException {
        return readEstateMate(filePath);
    }

    /**
     * Similar to {@link #readEstateMate()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyEstateMate> readEstateMate(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableEstateMate> jsonEstateMate = JsonUtil.readJsonFile(
                filePath, JsonSerializableEstateMate.class);
        if (!jsonEstateMate.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEstateMate.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveEstateMate(ReadOnlyEstateMate estateMate) throws IOException {
        saveEstateMate(estateMate, filePath);
    }

    /**
     * Similar to {@link #saveEstateMate(ReadOnlyEstateMate)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEstateMate(ReadOnlyEstateMate estateMate, Path filePath) throws IOException {
        requireNonNull(estateMate);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEstateMate(estateMate), filePath);
    }

}
