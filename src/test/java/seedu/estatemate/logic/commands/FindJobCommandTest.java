package seedu.estatemate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.logic.Messages.MESSAGE_JOBS_LISTED_OVERVIEW;
import static seedu.estatemate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.estatemate.testutil.TypicalJobs.REPAIR_AIR_CONDITIONER_COMPLETE;
import static seedu.estatemate.testutil.TypicalJobs.REPAIR_BROKEN_DOOR;
import static seedu.estatemate.testutil.TypicalJobs.getTypicalEstateMate;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.ModelManager;
import seedu.estatemate.model.UserPrefs;
import seedu.estatemate.model.job.JobContainsKeywordsPredicate;

public class FindJobCommandTest {
    private Model model = new ModelManager(getTypicalEstateMate(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalEstateMate(), new UserPrefs());

    @Test
    public void equals() {
        JobContainsKeywordsPredicate firstPredicate =
                new JobContainsKeywordsPredicate(Collections.singletonList("first"));
        JobContainsKeywordsPredicate secondPredicate =
                new JobContainsKeywordsPredicate(Collections.singletonList("second"));

        FindJobCommand findJobFirstCommand = new FindJobCommand(firstPredicate);
        FindJobCommand findJobSecondCommand = new FindJobCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findJobFirstCommand.equals(findJobFirstCommand));

        // same values -> returns true
        FindJobCommand findJobFirstCommandCopy = new FindJobCommand(firstPredicate);
        assertTrue(findJobFirstCommand.equals(findJobFirstCommandCopy));

        // different types -> returns false
        assertFalse(findJobFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findJobFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findJobFirstCommand.equals(findJobSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noJobFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        JobContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindJobCommand command = new FindJobCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredJobList());
    }

    @Test
    public void execute_multipleKeywords_multipleJobsFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 2);
        JobContainsKeywordsPredicate predicate = preparePredicate("Repair");
        FindJobCommand command = new FindJobCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(REPAIR_AIR_CONDITIONER_COMPLETE, REPAIR_BROKEN_DOOR), model.getFilteredJobList());
    }

    @Test
    public void toStringMethod() {
        JobContainsKeywordsPredicate predicate = new JobContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindJobCommand findJobCommand = new FindJobCommand(predicate);
        String expected = FindJobCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findJobCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private JobContainsKeywordsPredicate preparePredicate(String userInput) {
        return new JobContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
