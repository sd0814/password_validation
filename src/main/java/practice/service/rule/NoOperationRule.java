package practice.service.rule;

/**
 * Default implementation.
 */
public class NoOperationRule extends ValidationRule {
    @Override
    boolean validateInternal(String text) {
        return true;
    }
}
