package seedu.estatemate.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.estatemate.testutil.JobBuilder;

public class JobContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        JobContainsKeywordsPredicate firstPredicate = new JobContainsKeywordsPredicate(firstPredicateKeywordList);
        JobContainsKeywordsPredicate secondPredicate = new JobContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobContainsKeywordsPredicate firstPredicateCopy = new JobContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        JobContainsKeywordsPredicate predicate = new JobContainsKeywordsPredicate(Collections.singletonList("Repair"));
        assertTrue(predicate.test(new JobBuilder().withDescription("Repair light").build()));

        // Multiple keywords
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Repair", "light"));
        assertTrue(predicate.test(new JobBuilder().withDescription("Repair light").build()));

        // Only one matching keyword
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Repair", "light"));
        assertTrue(predicate.test(new JobBuilder().withDescription("Repair door").build()));

        // Mixed-case keywords
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("rEpaIR", "lIGhT"));
        assertTrue(predicate.test(new JobBuilder().withDescription("Repair light").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        JobContainsKeywordsPredicate predicate = new JobContainsKeywordsPredicate(keywords);

        String expected = JobContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
