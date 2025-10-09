package seedu.estatemate.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.estatemate.commons.util.ToStringBuilder;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.person.UniquePersonList;
import seedu.estatemate.model.job.Job;
import seedu.estatemate.model.job.UniqueJobList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EstateMate implements ReadOnlyEstateMate {

    private final UniquePersonList persons;
    private final UniqueJobList jobs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        jobs = new UniqueJobList();
    }

    public EstateMate() {}

    /**
     * Creates an EstateMate using the Persons in the {@code toBeCopied}
     */
    public EstateMate(ReadOnlyEstateMate toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code EstateMate} with {@code newData}.
     */
    public void resetData(ReadOnlyEstateMate newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code EstateMate}.
     * {@code key} must exist in the EstateMate object.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void addJob(Job job) {
        requireNonNull(job);
        jobs.add(job); // relies on UniqueJobList's identity-by-id
    }
    public boolean hasJobId(int id) {
        // Use the backing ObservableList to stream
        return jobs.asUnmodifiableObservableList()
                .stream()
                .anyMatch(j -> j.getId() == id);
    }

    public void removeJobById(int id) {
        Job toRemove = jobs.asUnmodifiableObservableList()
                .stream()
                .filter(j -> j.getId() == id)
                .findFirst()
                .orElse(null);
        if (toRemove != null) {
            jobs.remove(toRemove);
        }
    }

    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    public int nextJobId() {
        // max(existing id) + 1, or 1 if empty
        return jobs.asUnmodifiableObservableList()
                .stream()
                .mapToInt(Job::getId)
                .max()
                .orElse(0) + 1;
    }
    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EstateMate)) {
            return false;
        }

        EstateMate otherEstateMate = (EstateMate) other;
        return persons.equals(otherEstateMate.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
