package practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import practice.service.rule.ValidationRule;

import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
@EnableCaching
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ValidationRule.class))
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application is ready to serve.");
    }

    @PreDestroy
    public void onExit() {
        log.info("Application out.");
    }
}
