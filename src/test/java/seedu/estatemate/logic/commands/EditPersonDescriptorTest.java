package seedu.estatemate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_LEASE_AMOUNT_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_LEASE_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PAY_DATE_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.estatemate.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.estatemate.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different lease -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withLease(VALID_LEASE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different lease amounts -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withLeaseAmount(VALID_LEASE_AMOUNT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different pay date -> return false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPayDate(VALID_PAY_DATE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phone="
                + editPersonDescriptor.getPhone().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", address="
                + editPersonDescriptor.getAddress().orElse(null) + ", lease="
                + editPersonDescriptor.getLease().orElse(null) + ", lease amount="
                + editPersonDescriptor.getLeaseAmount().orElse(null) + ", pay date="
                + editPersonDescriptor.getPayDate().orElse(null) + ", tags="
                + editPersonDescriptor.getTags().orElse(null) + ", jobs="
                + editPersonDescriptor.getJobs().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
