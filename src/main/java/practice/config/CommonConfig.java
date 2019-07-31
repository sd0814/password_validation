package practice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Configuration
public class CommonConfig {
    private static final String DEFAULT_CONFIG_FILE = "/application.yml";

    public PropertySourcesPlaceholderConfigurer properties(Environment environment) {
        List<Properties> propertiesList = new ArrayList<>();
        addYmlProperties(propertiesList, DEFAULT_CONFIG_FILE);

        for (String activeProfile : environment.getActiveProfiles()) {
            String envConfigFileName = String.format("/application-%s.yml", activeProfile);
            addYmlProperties(propertiesList, envConfigFileName);
        }

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setPropertiesArray(propertiesList.toArray(new Properties[0]));
        return configurer;
    }

    private void addYmlProperties(List<Properties> propertiesList, String resourcePath) {
        ClassPathResource classPathResource = new ClassPathResource(resourcePath);

        try {
            if (classPathResource.getInputStream() != null) {
                log.debug("Load configuration file {}", resourcePath);

                YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
                bean.setResources(classPathResource);

                propertiesList.add(bean.getObject());
            }
        } catch (IOException e) {
            log.warn("Cannot load configuration file {}", resourcePath);
        }
    }
}
