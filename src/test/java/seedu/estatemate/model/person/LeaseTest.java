package seedu.estatemate.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeaseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lease(null));
    }

    @Test
    public void constructor_invalidLease_throwsIllegalArgumentException() {
        String invalidLease = "";
        assertThrows(IllegalArgumentException.class, () -> new Lease(invalidLease));
    }

    @Test
    public void isValidLease() {
        // null lease
        assertThrows(NullPointerException.class, () -> Lease.isValidLease(null));

        // invalid leases
        assertFalse(Lease.isValidLease("")); // empty string
        assertFalse(Lease.isValidLease(" ")); // spaces only

        // valid leases
        assertTrue(Lease.isValidLease("2025-01-01 2030-01-02"));
        assertTrue(Lease.isValidLease("2027-12-12 2030-12-12"));
        assertTrue(Lease.isValidLease("-")); // one character
        assertTrue(Lease.isValidLease("the sun rises from the east")); // generic non-empty text
    }

    @Test
    public void equals() {
        Lease lease = new Lease("Valid Lease");

        // same values -> returns true
        assertTrue(lease.equals(new Lease("Valid Lease")));

        // same object -> returns true
        assertTrue(lease.equals(lease));

        // null -> returns false
        assertFalse(lease.equals(null));

        // different types -> returns false
        assertFalse(lease.equals(5.0f));

        // different values -> returns false
        assertFalse(lease.equals(new Lease("Other Valid Lease")));
    }
}
