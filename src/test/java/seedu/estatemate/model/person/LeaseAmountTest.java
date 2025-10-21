package seedu.estatemate.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeaseAmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LeaseAmount(null));
    }

    @Test
    public void constructor_invalidLeaseAmount_throwsIllegalArgumentException() {
        String invalidLeaseAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new LeaseAmount(invalidLeaseAmount));
    }

    @Test
    public void isValidLeaseAmount() {
        // null lease amount
        assertThrows(NullPointerException.class, () -> LeaseAmount.isValidLeaseAmount(null));

        // invalid lease amounts
        assertFalse(LeaseAmount.isValidLeaseAmount("")); // empty string
        assertFalse(LeaseAmount.isValidLeaseAmount(" ")); // spaces only

        // valid lease amounts
        assertTrue(LeaseAmount.isValidLeaseAmount("760.00"));
        assertTrue(LeaseAmount.isValidLeaseAmount("1200.50"));
        assertTrue(LeaseAmount.isValidLeaseAmount("-")); // one character
        assertTrue(LeaseAmount.isValidLeaseAmount("the sun rises from the east")); // generic non-empty text
    }

    @Test
    public void equals() {
        LeaseAmount leaseAmount = new LeaseAmount("Valid Lease Amount");

        // same values -> returns true
        assertTrue(leaseAmount.equals(new LeaseAmount("Valid Lease Amount")));

        // same object -> returns true
        assertTrue(leaseAmount.equals(leaseAmount));

        // null -> returns false
        assertFalse(leaseAmount.equals(null));

        // different types -> returns false
        assertFalse(leaseAmount.equals(5.0f));

        // different values -> returns false
        assertFalse(leaseAmount.equals(new LeaseAmount("Other Valid Lease Amount")));
    }
}
