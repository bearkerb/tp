package seedu.estatemate.logic.parser;

import static seedu.estatemate.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.estatemate.model.job.Description;
import seedu.estatemate.logic.commands.AddJobCommand;
import seedu.estatemate.logic.parser.exceptions.ParseException;



public class AddJobCommandParser implements Parser<AddJobCommand> {
    public AddJobCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);
        if (!map.getValue(PREFIX_DESCRIPTION).isPresent() || !map.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE));
        }
        Description desc = ParserUtil.parseDescription(map.getValue(PREFIX_DESCRIPTION).get());
        // id is assigned in the command via model.nextJobId()
        return new AddJobCommand(desc);
    }
}
