package seedu.estatemate.testutil;

import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_LEASE_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_LEASE_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withLease("2025-01-01 2030-01-02")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withLease("2027-12-12 2030-12-12")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withLease("2026-01-05 2026-12-05").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withLease("2025-06-12 2030-12-25")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withLease("2024-03-21 2028-09-21").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withLease("2027-04-15 2030-08-02").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withLease("2026-11-12 2030-05-16").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withLease("2026-04-08 2030-04-25").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withLease("2025-07-17 2030-03-30").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withLease(VALID_LEASE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withLease(VALID_LEASE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code EstateMate} with all the typical persons.
     */
    public static EstateMate getTypicalEstateMate() {
        EstateMate ab = new EstateMate();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
