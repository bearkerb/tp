package seedu.estatemate.model;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.estatemate.commons.core.GuiSettings;
import seedu.estatemate.commons.core.LogsCenter;
import seedu.estatemate.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EstateMate estateMate;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given EstateMate and userPrefs.
     */
    public ModelManager(ReadOnlyEstateMate EstateMate, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(EstateMate, userPrefs);

        logger.fine("Initializing with address book: " + EstateMate + " and user prefs " + userPrefs);

        this.estateMate = new EstateMate(EstateMate);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.estateMate.getPersonList());
    }

    public ModelManager() {
        this(new EstateMate(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEstateMateFilePath() {
        return userPrefs.getEstateMateFilePath();
    }

    @Override
    public void setEstateMateFilePath(Path estateMateFilePath) {
        requireNonNull(estateMateFilePath);
        userPrefs.setEstateMateFilePath(estateMateFilePath);
    }

    //=========== EstateMate ================================================================================

    @Override
    public void setEstateMate(ReadOnlyEstateMate estateMate) {
        this.estateMate.resetData(estateMate);
    }

    @Override
    public ReadOnlyEstateMate getEstateMate() {
        return estateMate;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return estateMate.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        estateMate.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        estateMate.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        estateMate.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEstateMate}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager otherModelManager)) {
            return false;
        }

        return estateMate.equals(otherModelManager.estateMate)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
