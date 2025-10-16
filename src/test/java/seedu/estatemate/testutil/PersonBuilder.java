package seedu.estatemate.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.estatemate.model.person.Address;
import seedu.estatemate.model.person.Email;
import seedu.estatemate.model.person.Lease;
import seedu.estatemate.model.person.LeaseAmount;
import seedu.estatemate.model.person.Name;
import seedu.estatemate.model.person.PayDate;
import seedu.estatemate.model.person.Person;
import seedu.estatemate.model.person.Phone;
import seedu.estatemate.model.tag.Tag;
import seedu.estatemate.model.util.SampleDataUtil;

/**
 * A utility class to help with building of Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_LEASE = "2025-01-01 2027-01-01";
    public static final String DEFAULT_LEASE_AMOUNT = "950.00";
    public static final String DEFAULT_PAY_DATE = "2025-02-01";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Lease lease;
    private LeaseAmount leaseAmount;
    private PayDate payDate;
    private Set<Tag> tags;
    private List<Integer> jobs;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        lease = new Lease(DEFAULT_LEASE);
        leaseAmount = new LeaseAmount(DEFAULT_LEASE_AMOUNT);
        payDate = new PayDate(DEFAULT_PAY_DATE);
        tags = new HashSet<>();
        jobs = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        lease = personToCopy.getLease();
        leaseAmount = personToCopy.getLeaseAmount();
        payDate = personToCopy.getPayDate();
        tags = new HashSet<>(personToCopy.getTags());
        jobs = new ArrayList<>(personToCopy.getJobs());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code jobs} into a {@code List<Integer>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withJobs(String ... jobs) {
        this.jobs = SampleDataUtil.getJobList(jobs);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Lease} of the {@code Person} that we are building.
     */
    public PersonBuilder withLease(String lease) {
        this.lease = new Lease(lease);
        return this;
    }

    /**
     * Sets the {@code LeaseAmount} of the {@code Person} that we are building.
     */
    public PersonBuilder withLeaseAmount(String leaseAmount) {
        this.leaseAmount = new LeaseAmount(leaseAmount);
        return this;
    }

    /**
     * Sets the {@code PayDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withPayDate(String payDate) {
        this.payDate = new PayDate(payDate);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, lease, leaseAmount, payDate, tags, jobs);
    }

}
