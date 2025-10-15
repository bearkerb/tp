package seedu.estatemate.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.ReadOnlyEstateMate;
import seedu.estatemate.model.person.Address;
import seedu.estatemate.model.person.Email;
import seedu.estatemate.model.person.Lease;
import seedu.estatemate.model.person.Name;
import seedu.estatemate.model.person.PayDate;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.person.Phone;
import seedu.estatemate.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EstateMate} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Lease("2025-08-12 2027-09-12"),
                    new PayDate("2025-09-12"), getTagSet("friends"), new ArrayList<>()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Lease("2025-12-15 2030-02-01"),
                    new PayDate("2026-01-15"), getTagSet("colleagues", "friends"), new ArrayList<>()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Lease("2025-08-31 2028-04-05"),
                    new PayDate("2025-09-31"), getTagSet("neighbours"), new ArrayList<>()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Lease("2026-06-20 2028-06-20"),
                    new PayDate("2025-07-20"), getTagSet("family"), new ArrayList<>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Lease("2025-12-21 2027-12-22"),
                    new PayDate("2026-01-21"), getTagSet("classmates"), new ArrayList<>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Lease("2026-01-03 2027-06-03"),
                    new PayDate("2026-02-03"), getTagSet("colleagues"), new ArrayList<>())
        };
    }

    public static ReadOnlyEstateMate getSampleEstateMate() {
        EstateMate sampleAb = new EstateMate();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static List<Integer> getJobList(String... strings) {
        return Arrays.stream(strings)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
