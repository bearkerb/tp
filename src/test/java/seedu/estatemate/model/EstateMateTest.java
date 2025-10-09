package seedu.estatemate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.estatemate.testutil.Assert.assertThrows;
import static seedu.estatemate.testutil.TypicalPersons.ALICE;
import static seedu.estatemate.testutil.TypicalPersons.getTypicalEstateMate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.person.UniquePersonList;
import seedu.estatemate.model.person.exceptions.DuplicatePersonException;
import seedu.estatemate.testutil.PersonBuilder;
import seedu.estatemate.model.job.Job;
import seedu.estatemate.model.job.UniqueJobList;

public class EstateMateTest {

    private final EstateMate estateMate = new EstateMate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), estateMate.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> estateMate.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEstateMate_replacesData() {
        EstateMate newData = getTypicalEstateMate();
        estateMate.resetData(newData);
        assertEquals(newData, estateMate);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        EstateMateStub newData = new EstateMateStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> estateMate.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> estateMate.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEstateMate_returnsFalse() {
        assertFalse(estateMate.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEstateMate_returnsTrue() {
        estateMate.addPerson(ALICE);
        assertTrue(estateMate.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEstateMate_returnsTrue() {
        estateMate.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(estateMate.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> estateMate.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = EstateMate.class.getCanonicalName() + "{persons=" + estateMate.getPersonList() + "}";
        assertEquals(expected, estateMate.toString());
    }

    /**
     * A stub ReadOnlyEstateMate whose persons list can violate interface constraints.
     */
    private static class EstateMateStub implements ReadOnlyEstateMate {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final UniqueJobList jobs;

        {
            jobs = new UniqueJobList();
        }

        EstateMateStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Job> getJobList() {
            return jobs.asUnmodifiableObservableList();
        }
    }

}
