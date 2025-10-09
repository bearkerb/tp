package seedu.estatemate.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.estatemate.commons.util.ToStringBuilder;
import seedu.estatemate.logic.commands.exceptions.CommandException;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.job.Description;
import seedu.estatemate.model.job.Job;

/**
 * Adds a job to the address book.
 */
public class AddJobCommand extends Command {

    public static final String COMMAND_WORD = "job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "NAME ";

    public static final String MESSAGE_SUCCESS = "New Job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This Job already exists in the address book";

    private final Description description;

    /**
     * Creates an AddJobCommand to add the specified {@code Job}
     */
    public AddJobCommand(Description description) {
        this.description = requireNonNull(description);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int id = model.nextJobId();
        Job toAdd = new Job(description, id);
        model.addJob(toAdd);
        return new CommandResult(String.format("New job added: #%d %s", id, description));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddJobCommand otherAddJobCommand = (AddJobCommand) other;
        return description.equals(otherAddJobCommand.description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .toString();
    }
}
