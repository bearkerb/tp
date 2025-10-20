package seedu.estatemate.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's lease in EstateMate.
 * Guarantees: immutable; is valid as declared in {@link #isValidLease(String)}
 */
public class Lease {

    public static final String MESSAGE_CONSTRAINTS = "Leases can take any values, and it should not be blank";

    /*
     * The first character of the lease must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final LocalDate startDate;
    private final LocalDate endDate;
    public final String value;

    /**
     * Constructs a {@code Lease}.
     *
     * @param lease A valid lease.
     */
    public Lease(String lease) {
        requireNonNull(lease);
        checkArgument(isValidLease(lease), MESSAGE_CONSTRAINTS);
        value = lease;
    }

    /**
     * Returns true if a given string is a valid lease.
     */
    public static boolean isValidLease(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lease)) {
            return false;
        }

        Lease otherLease = (Lease) other;
        return value.equals(otherLease.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
