package seedu.estatemate.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.estatemate.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.estatemate.commons.core.index.Index;
import seedu.estatemate.logic.Messages;
import seedu.estatemate.logic.commands.exceptions.CommandException;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.person.Address;
import seedu.estatemate.model.person.Email;
import seedu.estatemate.model.person.Lease;
import seedu.estatemate.model.person.Name;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.person.Phone;
import seedu.estatemate.model.tag.Tag;

public class LinkCommand extends Command {
    public static final String COMMAND_WORD = "link";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a job to a tenant. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_JOB + "JOB NUMBER"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_JOB + "1 ";

    public static final String MESSAGE_LINK_JOB_SUCCESS = "New Job linked: %1$s";
    public static final String MESSAGE_ALREADY_LINKED_JOB = "This job is already linked to this tenant!";

    private final Index index;
    private final Integer job;
    public LinkCommand(Index index, Integer job) {
        requireAllNonNull(index, job);
        this.index = index;
        this.job = job;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToLink = lastShownList.get(index.getZeroBased());
        Person linkedPerson = createPersonWithJob(personToLink, job);
        model.setPerson(personToLink, linkedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_LINK_JOB_SUCCESS, Messages.format(linkedPerson)));
    }

    private static Person createPersonWithJob(Person personToEdit, Integer job) {
        assert personToEdit != null;

        Name originalName = personToEdit.getName();
        Phone originalPhone = personToEdit.getPhone();
        Email originalEmail = personToEdit.getEmail();
        Address originalAddress = personToEdit.getAddress();
        Lease originalLease = personToEdit.getLease();
        Set<Tag> originalTags = personToEdit.getTags();
        List<Integer> jobs = personToEdit.getJobs();
        jobs.add(job);

        return new Person(originalName, originalPhone, originalEmail, originalAddress, originalLease, originalTags,
                jobs);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkCommand)) {
            return false;
        }

        LinkCommand e = (LinkCommand) other;
        return index.equals(e.index)
                && job.equals(e.job);
    }
}
