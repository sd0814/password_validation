package practice.service.rule;

import org.apache.commons.lang3.StringUtils;

/**
 * Check: the text contains valid characters.
 */
public class CharacterRule extends ValidationRule {
    private static final String VALID_DIGITS = "1234567890";
    private static final String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    @Override
    boolean validateInternal(String text) {
        return StringUtils.containsOnly(text, VALID_DIGITS + VALID_CHARACTERS)
                && StringUtils.containsAny(text, VALID_CHARACTERS)
                && StringUtils.containsAny(text, VALID_DIGITS);
    }
}
