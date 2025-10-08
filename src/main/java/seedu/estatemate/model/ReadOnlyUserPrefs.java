package seedu.estatemate.model;

import java.nio.file.Path;

import seedu.estatemate.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getEstateMateFilePath();

}
