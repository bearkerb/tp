package seedu.estatemate.testutil;

import seedu.estatemate.model.job.Description;
import seedu.estatemate.model.job.Job;

/**
 * A utility class to help with building of Job objects.
 */
public class JobBuilder {
    public static final int DEFAULT_ID = 1;
    public static final String DEFAULT_DESCRIPTION = "Fix faulty light";

    private Description description;
    private int id;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        id = DEFAULT_ID;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        description = jobToCopy.getDescription();
        id = jobToCopy.getId();
    }

    /**
     * Sets the {@code Description} of the {@code Job} that we are building.
     */
    public JobBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Job} that we are building.
     */
    public JobBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public Job build() {
        return new Job(description, id);
    }

    public Job buildComplete() {
        Job j = new Job(description,id);
        j.setDone(true);
        return j;
    }
}
