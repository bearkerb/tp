package seedu.estatemate.logic.commands;

import static seedu.estatemate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.estatemate.testutil.TypicalPersons.getTypicalEstateMate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.estatemate.model.Model;
import seedu.estatemate.model.ModelManager;
import seedu.estatemate.model.UserPrefs;

public class ListJobCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEstateMate(), new UserPrefs());
        expectedModel = new ModelManager(model.getEstateMate(), new UserPrefs());
    }

    @Test
    public void execute_jobListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListJobCommand(), model, ListJobCommand.MESSAGE_SUCCESS
                + expectedModel.getFilteredJobList().toString(), expectedModel);
    }

    @Test
    public void execute_jobListIsFiltered_showsEverything() {
        // showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListJobCommand(), model, ListJobCommand.MESSAGE_SUCCESS
                + expectedModel.getFilteredJobList().toString(), expectedModel);
    }
}
