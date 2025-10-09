package seedu.estatemate.logic.parser;

import static seedu.estatemate.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.estatemate.logic.commands.DeleteJobCommand;
import seedu.estatemate.logic.parser.exceptions.ParseException;
import seedu.estatemate.model.job.Description;


public class DeleteJobCommandParser implements Parser<DeleteJobCommand> {
    public DeleteJobCommand parse(String args) throws ParseException {
        String trimmed = args.trim();
        if (trimmed.isEmpty() || !trimmed.matches("\\d+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteJobCommand.MESSAGE_USAGE));
        }
        return new DeleteJobCommand(Integer.parseInt(trimmed));
    }
}
