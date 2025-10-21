package seedu.estatemate.ui;

import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.person.Person;

/**
 * An UI component that displays information of a {@code Tenant}.
 */
public class TenantCard extends UiPart<Region> {

    private static final String FXML = "TenantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    public final Model model;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label lease;
    @FXML
    private Label leaseAmount;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label maintenanceTitle;
    @FXML
    private Label maintenanceId;
    @FXML
    private Label maintenanceDescription;
    @FXML
    private Label payDate;

    /**
     * Creates a {@code PersonCode} with the given {@code Person}, index and {@code ModelManager} to display.
     */
    public TenantCard(Person person, int displayedIndex, Model model) {
        super(FXML);
        this.person = person;
        this.model = model;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Phone Number: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        lease.setText("Lease Start-End: " + person.getLease().value);
        leaseAmount.setText("Lease Amount: " + person.getLeaseAmount().toDisplayValue());
        payDate.setText("Pay Date: " + person.getPayDate().value);
        email.setText("Email: " + person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        maintenanceTitle.setText("Maintenance Information:");
        displayMaintenanceInformation();

    }

    private void displayMaintenanceInformation() {
        List<Integer> jobIds = model.getJobIdsForPerson(person);
        if (jobIds.isEmpty()) {
            maintenanceId.setText("Job Number: -");
            maintenanceDescription.setText("Job Description: No maintenance jobs yet");
            return;
        } else {
            StringBuilder jobNumbers = new StringBuilder();
            for (int i = 0; i < jobIds.size(); i++) {
                jobNumbers.append(jobIds.get(i));
                if (i != jobIds.size() - 1) {
                    jobNumbers.append(", ");
                }
            }
            maintenanceId.setText("Job Number(s): " + jobNumbers);

            StringBuilder jobDescriptions = new StringBuilder("Job Description(s):\n");
            for (Integer id : jobIds) {
                String eachDescription = model.getJobDescriptionById(id);
                boolean isCompleted = model.isJobCompleted(id);
                String status = isCompleted ? "completed ✅" : "not completed ❌";
                jobDescriptions.append("#").append(id).append(" ").append(eachDescription)
                        .append(" (status: ").append(status).append(")\n");
            }
            maintenanceDescription.setText(jobDescriptions.toString().trim());
        }
    }
}
