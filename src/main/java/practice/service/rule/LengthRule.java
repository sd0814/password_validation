package practice.service.rule;

import org.springframework.beans.factory.annotation.Value;

public class LengthRule extends ValidationRule {
    @Value("${application.rules.length-min}")
    private int minLength;

    @Value("${application.rules.length-max}")
    private int maxLength;

    @Override
    boolean validateInternal(String text) {
        if (text == null) {
            return false;
        }

        int textLength = text.length();
        return minLength <= textLength && textLength <= maxLength;
    }
}
