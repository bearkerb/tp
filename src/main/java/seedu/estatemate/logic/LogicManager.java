package seedu.estatemate.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.estatemate.commons.core.GuiSettings;
import seedu.estatemate.commons.core.LogsCenter;
import seedu.estatemate.logic.commands.Command;
import seedu.estatemate.logic.commands.CommandResult;
import seedu.estatemate.logic.commands.exceptions.CommandException;
import seedu.estatemate.logic.parser.EstateMateParser;
import seedu.estatemate.logic.parser.exceptions.ParseException;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.ReadOnlyEstateMate;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final EstateMateParser estateMateParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        estateMateParser = new EstateMateParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = estateMateParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveEstateMate(model.getEstateMate());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEstateMate getEstateMate() {
        return model.getEstateMate();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getEstateMateFilePath() {
        return model.getEstateMateFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
