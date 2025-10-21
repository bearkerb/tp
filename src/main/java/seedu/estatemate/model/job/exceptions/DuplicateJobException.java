package seedu.estatemate.model.job.exceptions;

/**
 * Signals that the operation would result in duplicate Jobs (same description).
 */
public class DuplicateJobException extends RuntimeException {
    public DuplicateJobException() {
        super("Operation would result in duplicate jobs");
    }
}
