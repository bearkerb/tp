package seedu.estatemate.testutil;

import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.person.Person;

/**
 * A utility class to help with building EstateMate objects.
 * Example usage: <br>
 *     {@code EstateMate ab = new EstateMateBuilder().withPerson("John", "Doe").build();}
 */
public class EstateMateBuilder {

    private EstateMate estateMate;

    public EstateMateBuilder() {
        estateMate = new EstateMate();
    }

    public EstateMateBuilder(EstateMate estateMate) {
        this.estateMate = estateMate;
    }

    /**
     * Adds a new {@code Person} to the {@code EstateMate} that we are building.
     */
    public EstateMateBuilder withPerson(Person person) {
        estateMate.addPerson(person);
        return this;
    }

    public EstateMate build() {
        return estateMate;
    }
}
