package practice.service.rule;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LengthRuleTest {
    @Autowired
    private LengthRule lengthRule;

    @Value("${application.rules.length-min}")
    private int minLength;

    @Value("${application.rules.length-max}")
    private int maxLength;

    /**
     * <b>test method</b>: <code>LengthRule#validateInternal</code><br />
     * <b>test condition</b>: input a string which length is between minLength ~ maxLength<br />
     * <b>expects</b>: inputs are all valid
     */
    @Test
    public void testValidate_valid() {
        String testText = RandomStringUtils.random(minLength);
        boolean valid = lengthRule.validateInternal(testText);
        Assert.assertTrue(valid);

        String testText1 = RandomStringUtils.random(maxLength);
        boolean valid1 = lengthRule.validateInternal(testText1);
        Assert.assertTrue(valid1);
    }

    /**
     * <b>test method</b>: <code>LengthRule#validateInternal</code><br />
     * <b>test condition</b>: input strings which length is shorter than minLength / longer than maxLength<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid() {
        String testTextShort = RandomStringUtils.random(Math.min(minLength - 1, 0));
        boolean validShort = lengthRule.validate(testTextShort);
        Assert.assertFalse(validShort);

        String testTextLong = RandomStringUtils.random(maxLength + 1);
        boolean validLong = lengthRule.validate(testTextLong);
        Assert.assertFalse(validLong);
    }
}
