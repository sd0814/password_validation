package practice.service.rule;

import java.util.stream.IntStream;

/**
 * Validate: Must not contain any sequence of characters immediately followed by the same sequence.
 * Valid: "qwetqwe"
 * Invalid: "qweqwe", "1qweqwe2", "qwee12"
 */
public class SequenceRule extends ValidationRule {
    @Override
    boolean validateInternal(String text) {
        boolean valid = true;

        char[] chars = text.toCharArray();

        boolean repeated;
        for (int ii = 1; ii < chars.length; ii++) {
            int tailLength = ii + 1;
            int compareTimes = tailLength / 2;
            int startIndex1 = chars.length - ii - 1;

            repeated = IntStream.range(0, compareTimes)
                    .anyMatch(compareTime -> {
                        int compareLength = compareTime + 1;
                        int startIndex2 = startIndex1 + compareLength;
                        return IntStream.range(0, compareLength)
                                .allMatch(index -> chars[index + startIndex1] == chars[index + startIndex2]);
                    });

            if (repeated) {
                valid = false;
                break;
            }
        }

        return valid;
    }
}
