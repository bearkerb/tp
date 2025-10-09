package seedu.estatemate.model;

import javafx.collections.ObservableList;
import seedu.estatemate.model.job.Job;
import seedu.estatemate.model.person.Person;

/**
 * Unmodifiable view of an EstateMate object
 */
public interface ReadOnlyEstateMate {

    /**
     * Returns an unmodifiable view of the persons list. This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable job list.
     */
    ObservableList<Job> getJobList();
}
