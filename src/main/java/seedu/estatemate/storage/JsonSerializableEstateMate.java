package seedu.estatemate.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.estatemate.commons.exceptions.IllegalValueException;
import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.ReadOnlyEstateMate;
import seedu.estatemate.model.person.Person;

/**
 * An Immutable EstateMate that is serializable to JSON format.
 */
@JsonRootName(value = "estatemate")
class JsonSerializableEstateMate {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEstateMate} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEstateMate(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyEstateMate} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEstateMate}.
     */
    public JsonSerializableEstateMate(ReadOnlyEstateMate source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this EstateMate object into the model's {@code EstateMate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EstateMate toModelType() throws IllegalValueException {
        EstateMate estateMate = new EstateMate();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (estateMate.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            estateMate.addPerson(person);
        }
        return estateMate;
    }

}
