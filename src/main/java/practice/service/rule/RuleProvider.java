package practice.service.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleProvider {
    @Autowired
    @Qualifier("validationRules")
    private List<ValidationRule> validationRules;

    public ValidationRule getStartRule() {
        if (validationRules.isEmpty()) {
            return null;
        }
        return validationRules.get(0);
    }
}
