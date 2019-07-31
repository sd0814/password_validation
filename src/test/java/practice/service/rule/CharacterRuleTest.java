package practice.service.rule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterRuleTest {
    @Autowired
    private CharacterRule characterRule;

    /**
     * <b>test method</b>: <code>CharacterRule#validateInternal</code><br />
     * <b>test condition</b>: input a set of mixtures of lowercase letters and numerical digits<br />
     * <b>expects</b>: inputs are all valid
     */
    @Test
    public void testValidate_valid() {
        String testText = "qw12";
        boolean valid = characterRule.validateInternal(testText);
        Assert.assertTrue(valid);

        String testText2 = "12qw";
        boolean valid2 = characterRule.validateInternal(testText2);
        Assert.assertTrue(valid2);
    }

    /**
     * <b>test method</b>: <code>CharacterRule#validateInternal</code><br />
     * <b>test condition</b>: input an empty string<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid_empty() {
        String testText = "";
        boolean valid = characterRule.validateInternal(testText);
        Assert.assertFalse(valid);
    }

    /**
     * <b>test method</b>: <code>CharacterRule#validateInternal</code><br />
     * <b>test condition</b>: input - all digit<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid_digit_only() {
        String testText = "123";
        boolean valid = characterRule.validateInternal(testText);
        Assert.assertFalse(valid);
    }

    /**
     * <b>test method</b>: <code>CharacterRule#validateInternal</code><br />
     * <b>test condition</b>: input - all character<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid_character_only() {
        String testText = "abc";
        boolean valid = characterRule.validateInternal(testText);
        Assert.assertFalse(valid);
    }

    /**
     * <b>test method</b>: <code>CharacterRule#validateInternal</code><br />
     * <b>test condition</b>: input a set of mixtures of UPPERCASE letters and numerical digits<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid_character() {
        String testText = "QW12";
        boolean valid = characterRule.validateInternal(testText);
        Assert.assertFalse(valid);
    }

    /**
     * <b>test method</b>: <code>CharacterRule#validateInternal</code><br />
     * <b>test condition</b>: input a set of mixtures of lowercase letters, numerical digits, and symbols<br />
     * <b>expects</b>: validate fail
     */
    @Test
    public void testValidate_invalid_symbol() {
        String testText = "qw12+-*/";
        boolean valid = characterRule.validateInternal(testText);
        Assert.assertFalse(valid);
    }
}
