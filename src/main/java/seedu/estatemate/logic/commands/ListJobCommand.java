package seedu.estatemate.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.model.Model.PREDICATE_SHOW_ALL_JOBS;

import seedu.estatemate.model.Model;
/**
 * Lists all jobs to the user.
 */
public class ListJobCommand extends Command {
    public static final String COMMAND_WORD = "ljob";

    public static final String MESSAGE_SUCCESS = "Listed all jobs";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        String resultMessage = MESSAGE_SUCCESS + model.getFilteredJobList().toString();
        return new CommandResult(resultMessage);
    }
}
