package rujche.problem.reproduce.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import rujche.problem.reproduce.property.PropertyTwo;

// Make test passed option 2: Add "@EnableConfigurationProperties" here.
// @EnableConfigurationProperties
public class AutoConfigurationTwo extends AutoConfigurationBase {

    @Bean
    @ConfigurationProperties(PropertyTwo.PREFIX)
    PropertyTwo propertyTwo() {
        return new PropertyTwo();
    }
}
