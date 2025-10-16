package seedu.estatemate.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's lease amount in EstateMate.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeaseAmount(String)}
 */
public class LeaseAmount {

    public static final String MESSAGE_CONSTRAINTS = "Lease amounts can take any values, and it should not be blank";

    /*
     * The first character of the lease amount must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code LeaseAmount}.
     *
     * @param leaseAmount A valid lease amount.
     */
    public LeaseAmount(String leaseAmount) {
        requireNonNull(leaseAmount);
        checkArgument(isValidLeaseAmount(leaseAmount), MESSAGE_CONSTRAINTS);
        value = leaseAmount;
    }

    /**
     * Returns true if a given string is a valid lease amount.
     */
    public static boolean isValidLeaseAmount(String test) {
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
        if (!(other instanceof LeaseAmount)) {
            return false;
        }

        LeaseAmount otherLeaseAmount = (LeaseAmount) other;
        return value.equals(otherLeaseAmount.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
