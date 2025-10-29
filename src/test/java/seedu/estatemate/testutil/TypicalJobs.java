package seedu.estatemate.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.job.Job;
import seedu.estatemate.model.person.Person;

public class TypicalJobs {
    public static final Job FIX_LIGHT = new JobBuilder().withDescription("Fix faulty light").withId(1).build();
    public static final Job PAINT_WALLS = new JobBuilder().withDescription("Paint interior walls").withId(2).build();
    public static final Job REPAIR_AIR_CONDITIONER_COMPLETE = new JobBuilder().withDescription("Repair air conditioner")
            .withId(1).buildComplete();
    public static final Job UNCLOG_SINK = new JobBuilder().withDescription("Unclog sink").withId(1).build();
    public static final Job REPAIR_BROKEN_DOOR = new JobBuilder().withDescription("Repair broken door")
            .withId(1).build();

    private TypicalJobs() {};

    /**
     * Returns an {@code EstateMate} with all the typical jobs.
     */
    public static EstateMate getTypicalEstateMate() {
        EstateMate ab = new EstateMate();
        for (Job job : getTypicalJobs()) {
            ab.addJob(job);
        }
        return ab;
    }

    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(FIX_LIGHT, PAINT_WALLS, REPAIR_AIR_CONDITIONER_COMPLETE, UNCLOG_SINK,
                REPAIR_BROKEN_DOOR));
    }
}
