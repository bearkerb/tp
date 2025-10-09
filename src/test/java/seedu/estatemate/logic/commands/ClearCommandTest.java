package seedu.estatemate.logic.commands;

import static seedu.estatemate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.estatemate.testutil.TypicalPersons.getTypicalEstateMate;

import org.junit.jupiter.api.Test;

import seedu.estatemate.model.EstateMate;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.ModelManager;
import seedu.estatemate.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyEstateMate_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyEstateMate_success() {
        Model model = new ModelManager(getTypicalEstateMate(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalEstateMate(), new UserPrefs());
        expectedModel.setEstateMate(new EstateMate());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
