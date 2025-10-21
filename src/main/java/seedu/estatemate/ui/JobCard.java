package seedu.estatemate.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.estatemate.model.Model;
import seedu.estatemate.model.job.Job;

/**
 * An UI component that displays information of a {@code Job}.
 */
public class JobCard extends UiPart<Region> {

    private static final String FXML = "JobCard.fxml";

    public final Job job;
    private final Model model;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label status;

    /**
     * Creates a {@code JobCard} with the given {@code Job} and index to display.
     * It highlights the card based on the job's completion status.
     */
    public JobCard(Job job, int displayedIndex, Model model) {
        super(FXML);
        this.job = job;
        this.model = model;
        id.setText(displayedIndex + ". ");
        description.setText(job.getDescription().toString());

        if (job.getDone()) {
            status.setText("Completed");
            cardPane.setStyle("-fx-background-color: #388E3C; -fx-border-color: #388E3C;");
        } else {
            status.setText("Pending");
            cardPane.setStyle("-fx-background-color: #D32F2F; -fx-border-color: #D32F2F;");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof JobCard)) {
            return false;
        }

        JobCard otherJobCard = (JobCard) other;
        return id.getText().equals(otherJobCard.id.getText())
                && job.equals(otherJobCard.job);
    }
}