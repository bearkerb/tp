package seedu.estatemate.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_JOB;

import seedu.estatemate.commons.core.index.Index;
import seedu.estatemate.logic.commands.LinkCommand;
import seedu.estatemate.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LinkCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns an LinkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOB);

        if (!jobPrefixPresent(args)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE), pe);
        }

        // watch out for this, probably need more rigorous check for valid job
        Integer jobNumber = ParserUtil.parseJob(argMultimap.getValue(PREFIX_JOB).get());

        return new LinkCommand(index, jobNumber);
    }

    private static boolean jobPrefixPresent(String args) {
        return args.contains(PREFIX_JOB.toString());
    }
}
