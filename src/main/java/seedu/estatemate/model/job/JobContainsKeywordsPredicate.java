package seedu.estatemate.model.job;

import java.util.List;
import java.util.function.Predicate;

import seedu.estatemate.commons.util.StringUtil;
import seedu.estatemate.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Job}'s {@code Description} matches any of the keywords given.
 */
public class JobContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(job.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.estatemate.model.job.JobContainsKeywordsPredicate)) {
            return false;
        }

        seedu.estatemate.model.job.JobContainsKeywordsPredicate otherJobContainsKeywordsPredicate = (seedu.estatemate.model.job.JobContainsKeywordsPredicate) other;
        return keywords.equals(otherJobContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
