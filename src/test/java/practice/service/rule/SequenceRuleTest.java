package practice.service.rule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceRuleTest {
    @Autowired
    private SequenceRule sequenceRule;

    /**
     * <b>test method</b>: <code>SequenceRule#validateInternal</code><br />
     * <b>test condition</b>: input a string which has no repeat sequence<br />
     * <b>expects</b>: inputs are all valid
     */
    @Test
    public void testValidate_valid() {
        String testText = "qwetqwe";
        boolean valid = sequenceRule.validateInternal(testText);
        Assert.assertTrue(valid);
    }

    /**
     * <b>test method</b>: <code>SequenceRule#validateInternal</code><br />
     * <b>test condition</b>: input a string which has repeat sequence<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid() {
        String testText = "1qweqwe2";
        boolean valid = sequenceRule.validateInternal(testText);
        Assert.assertFalse(valid);

        String testText2 = "1qweqwe2";
        boolean valid2 = sequenceRule.validateInternal(testText2);
        Assert.assertFalse(valid2);

        String testText3 = "qwee12";
        boolean valid3 = sequenceRule.validateInternal(testText3);
        Assert.assertFalse(valid3);
    }
}
