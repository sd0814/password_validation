package practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import practice.service.rule.RuleProvider;

@Service
public class ValidationService {
    @Autowired
    private RuleProvider ruleProvider;

    @Cacheable("validate")
    public boolean validate(String text) {
        return ruleProvider.getStartRule().validate(text);
    }
}
