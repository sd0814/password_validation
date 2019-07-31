package practice.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceTest {
    @Autowired
    private ValidationService validationService;

    /**
     * <b>test method</b>: <code>ValidationService#validate</code><br />
     * <b>test condition</b>: input a set of mixtures of lowercase letters and numerical digits<br />
     * <b>expects</b>: inputs are all valid
     */
    @Test
    public void testValidate_valid() {
        String testText = "qwerty123456";
        boolean valid = validationService.validate(testText);
        Assert.assertTrue(valid);
    }

    /**
     * <b>test method</b>: <code>ValidationService#validate</code><br />
     * <b>test condition</b>: input a set of mixtures of lowercase letters and numerical digits<br />
     * <b>expects</b>: Validate fail
     */
    @Test
    public void testValidate_invalid() {
        String testText = "abcdef";
        boolean valid = validationService.validate(testText);
        Assert.assertFalse(valid);

        String testText1 = "12345";
        boolean valid1 = validationService.validate(testText1);
        Assert.assertFalse(valid1);

        String testText2 = "QWE";
        boolean valid2 = validationService.validate(testText2);
        Assert.assertFalse(valid2);

        String testText3 = "1qweqwe2";
        boolean valid3 = validationService.validate(testText3);
        Assert.assertFalse(valid3);

        String testText4 = "qw12";
        boolean valid4 = validationService.validate(testText4);
        Assert.assertFalse(valid4);
    }
}
