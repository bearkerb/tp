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

        // blank lease amount
        assertFalse(LeaseAmount.isValidLeaseAmount("")); // empty string
        assertFalse(LeaseAmount.isValidLeaseAmount(" ")); // spaces only

        // does not follow regex format
        assertFalse(LeaseAmount.isValidLeaseAmount("hello")); // non-amount input
        assertFalse(LeaseAmount.isValidLeaseAmount("hello.there")); // non-amount input with decimal point
        assertFalse(LeaseAmount.isValidLeaseAmount("hello.03")); // non-amount input with decimal digits
        assertFalse(LeaseAmount.isValidLeaseAmount("10.there")); // non-amount input with integer digits
        assertFalse(LeaseAmount.isValidLeaseAmount("1023a.02")); // non-digit character in integer digits
        assertFalse(LeaseAmount.isValidLeaseAmount("1023.2a")); // non-digit character in decimal digits

        // invalid amount format
        assertFalse(LeaseAmount.isValidLeaseAmount("1000.")); // no decimal digits
        assertFalse(LeaseAmount.isValidLeaseAmount(".50")); // no integer digits
        assertFalse(LeaseAmount.isValidLeaseAmount(".")); // no decimal and integer digits
        assertFalse(LeaseAmount.isValidLeaseAmount("1250")); // only integer digits
        assertFalse(LeaseAmount.isValidLeaseAmount("-5000.50")); // negative input
        assertFalse(LeaseAmount.isValidLeaseAmount("5000,50")); // comma instead of dot
        assertFalse(LeaseAmount.isValidLeaseAmount("10,000.50")); // comma for thousands format
        assertFalse(LeaseAmount.isValidLeaseAmount("-0.00")); // negative zero
        assertFalse(LeaseAmount.isValidLeaseAmount("825.0")); // one decimal place
        assertFalse(LeaseAmount.isValidLeaseAmount("825.005")); // three decimal place

        // valid lease amounts
        assertTrue(LeaseAmount.isValidLeaseAmount("0.00")); // zero
        assertTrue(LeaseAmount.isValidLeaseAmount("0000.00")); // leading zero
        assertTrue(LeaseAmount.isValidLeaseAmount("0000650.00")); // leading zero
        assertTrue(LeaseAmount.isValidLeaseAmount("0.50")); // zero for integer digit
        assertTrue(LeaseAmount.isValidLeaseAmount("650.00")); // zero for decimal digit
        assertTrue(LeaseAmount.isValidLeaseAmount("6534.32")); // non-zero integer and decimal digit
        assertTrue(LeaseAmount.isValidLeaseAmount("12345678901234567890.50")); // large number
    }

    @Test
    public void equals() {
        LeaseAmount leaseAmount = new LeaseAmount("1450.00");

        // same values -> returns true
        assertTrue(leaseAmount.equals(new LeaseAmount("1450.00")));

        // same object -> returns true
        assertTrue(leaseAmount.equals(leaseAmount));

        // null -> returns false
        assertFalse(leaseAmount.equals(null));

        // different types -> returns false
        assertFalse(leaseAmount.equals(5.0f));

        // different values -> returns false
        assertFalse(leaseAmount.equals(new LeaseAmount("850.00")));
    }
}
