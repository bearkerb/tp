package seedu.estatemate.model.job;

import static seedu.estatemate.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.estatemate.commons.util.ToStringBuilder;
import seedu.estatemate.model.person.Address;
import seedu.estatemate.model.person.Email;
import seedu.estatemate.model.person.Name;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.person.Phone;
import seedu.estatemate.model.tag.Tag;

/**
 * Represents a Job in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {
    private final Description description;

    /**
     * Every field must be present and not null.
     */
    public Job(Description description) {
        requireAllNonNull(description);
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both jobs have the same identity and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return description.equals(otherJob.description);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .toString();
    }
}
