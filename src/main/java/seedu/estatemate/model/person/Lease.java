package seedu.estatemate.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}-\\d{2}-\\d{2}";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final String value;

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a {@code Lease}.
     *
     * @param lease A valid lease of format "yyyy-MM-dd yyyy-MM-dd".
     */
    public Lease(String lease) {
        requireNonNull(lease);
        checkArgument(isValidLease(lease), MESSAGE_CONSTRAINTS);
        value = lease;

        String[] dates = getDates(lease);
        startDate = LocalDate.parse(dates[0], DATE_FORMATTER);
        endDate = LocalDate.parse(dates[1], DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is in a valid lease format.
     */
    public static boolean isValidLease(String test) {
        boolean isValidFormat = test.matches(VALIDATION_REGEX);
        boolean isValidDates = true;

        String[] dates = getDates(test);

        // Check that start and end dates are valid
        try {
            LocalDate startDate = LocalDate.parse(dates[0], DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(dates[1], DATE_FORMATTER);
            // Check that end date is on the day of start date or after
            if (endDate.isBefore(startDate)) {
                isValidDates = false;
            }
        } catch (DateTimeParseException e) {
            isValidDates = false;
        }

        return isValidFormat && isValidDates;
    }

    /**
     * Parses the lease for its start and end dates.
     * @return A string array with the first element as the start date and second element as the end date.
     */
    private static String[] getDates(String lease) {
        return lease.split(" ");
    }

    /**
     * Converts the lease into a more human-readable string representation, intended for display purposes.
     * @return A display friendly string representing the lease period.
     */
    public String toDisplayValue() {
        return startDate.format(DATE_FORMATTER) + " to " + endDate.format(DATE_FORMATTER);
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
        boolean isStartDateEquals = startDate.equals(otherLease.startDate);
        boolean isEndDateEquals = endDate.equals(otherLease.endDate);
        return isStartDateEquals && isEndDateEquals;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
