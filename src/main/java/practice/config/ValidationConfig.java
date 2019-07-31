package practice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.service.rule.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ValidationConfig {
    @Autowired
    private ApplicationContext context;

    @Value("${application.rules.applied-rules}")
    private List<String> appliedRules;

    @Bean
    public List<ValidationRule> validationRules() {
        List<ValidationRule> validationRules = new ArrayList<>();
        validationRules.add(noOperationRule());

        appliedRules.forEach(appliedRule -> {
            try {
                validationRules.add(context.getBean(appliedRule, ValidationRule.class));
            } catch (BeansException e) {
                log.warn("Cannot load validation rule: {}", appliedRule);
            }
        });

        for (int ii = 0; ii < validationRules.size() - 1; ii++) {
            ValidationRule thisRule = validationRules.get(ii);
            ValidationRule nextRule = validationRules.get(ii + 1);
            thisRule.setNextRule(nextRule);
        }

        return validationRules;
    }

    @Bean
    public NoOperationRule noOperationRule() {
        return new NoOperationRule();
    }

    @Bean
    public CharacterRule characterRule() {
        return new CharacterRule();
    }

    @Bean
    public LengthRule lengthRule() {
        return new LengthRule();
    }

    @Bean
    public SequenceRule sequenceRule() {
        return new SequenceRule();
    }
}
