package practice.service.rule;

import lombok.Setter;

/**
 * Base class of Validation Rule.
 * Concept: "chain of responsibility"
 */
public abstract class ValidationRule {
    @Setter
    private ValidationRule nextRule;

    private boolean hasNextRule() {
        return nextRule != null;
    }

    public final boolean validate(String text) {
        boolean result = validateInternal(text);

        if (result && hasNextRule()) {
            return nextRule.validate(text);
        }
        return result;
    }

    abstract boolean validateInternal(String text);
}
