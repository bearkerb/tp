package seedu.estatemate.testutil;

import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.estatemate.logic.commands.AddCommand;
import seedu.estatemate.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getJobs().forEach(job -> sb.append(PREFIX_JOB + job.toString() + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getJobs().isPresent()) {
            List<Integer> jobs = descriptor.getJobs().get();
            if (jobs.isEmpty()) {
                sb.append(PREFIX_JOB);
            } else {
                jobs.forEach(j -> sb.append(PREFIX_JOB).append(j.toString()).append(" "));
            }
        }
        return sb.toString();
    }
}
