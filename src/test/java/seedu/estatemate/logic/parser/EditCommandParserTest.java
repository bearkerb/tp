package seedu.estatemate.logic.parser;

import static seedu.estatemate.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.estatemate.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_LEASE_AMOUNT_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_LEASE_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_PAY_DATE_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.estatemate.logic.commands.CommandTestUtil.LEASE_AMOUNT_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.LEASE_AMOUNT_DESC_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.LEASE_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.LEASE_DESC_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.PAY_DATE_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.estatemate.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_LEASE_AMOUNT_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_LEASE_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PAY_DATE_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.estatemate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_LEASE;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_LEASE_AMOUNT;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.estatemate.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.estatemate.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.estatemate.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.estatemate.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.estatemate.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.estatemate.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.estatemate.commons.core.index.Index;
import seedu.estatemate.logic.Messages;
import seedu.estatemate.logic.commands.EditCommand;
import seedu.estatemate.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.estatemate.model.person.Address;
import seedu.estatemate.model.person.Email;
import seedu.estatemate.model.person.Lease;
import seedu.estatemate.model.person.LeaseAmount;
import seedu.estatemate.model.person.Name;
import seedu.estatemate.model.person.PayDate;
import seedu.estatemate.model.person.Phone;
import seedu.estatemate.model.tag.Tag;
import seedu.estatemate.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_LEASE_DESC, Lease.MESSAGE_CONSTRAINTS); // invalid lease
        assertParseFailure(parser, "1" + INVALID_LEASE_AMOUNT_DESC,
                LeaseAmount.MESSAGE_CONSTRAINTS); // invalid lease amount
        assertParseFailure(parser, "1" + INVALID_PAY_DATE_DESC, PayDate.MESSAGE_CONSTRAINTS); //invalid pay date
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY + VALID_LEASE_AMY + VALID_LEASE_AMOUNT_AMY + VALID_PAY_DATE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + LEASE_DESC_AMY + LEASE_AMOUNT_DESC_AMY + PAY_DATE_DESC_AMY
                + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withLease(VALID_LEASE_AMY).withLeaseAmount(VALID_LEASE_AMOUNT_AMY).withPayDate(VALID_PAY_DATE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // lease
        userInput = targetIndex.getOneBased() + LEASE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLease(VALID_LEASE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // lease amount
        userInput = targetIndex.getOneBased() + LEASE_AMOUNT_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLeaseAmount(VALID_LEASE_AMOUNT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // pay date
        userInput = targetIndex.getOneBased() + PAY_DATE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPayDate(VALID_PAY_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddTenantCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + LEASE_DESC_AMY
                + LEASE_AMOUNT_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + LEASE_DESC_BOB + LEASE_AMOUNT_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_LEASE, PREFIX_LEASE_AMOUNT));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_LEASE_DESC
                + INVALID_LEASE_AMOUNT_DESC + INVALID_EMAIL_DESC + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC
                + INVALID_EMAIL_DESC + INVALID_LEASE_DESC + INVALID_LEASE_AMOUNT_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_LEASE, PREFIX_LEASE_AMOUNT));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
