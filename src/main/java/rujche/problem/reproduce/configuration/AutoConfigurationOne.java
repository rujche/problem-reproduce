package rujche.problem.reproduce.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import rujche.problem.reproduce.property.PropertyOne;

@ConditionalOnBean(AutoConfigurationOne.AutoConfigurationOneDemoBean.class) // Make test passed option 3: Delete current line.
// @ConditionalOnProperty(value = "auto-configuration-one.enabled", havingValue = "true")
public class AutoConfigurationOne extends AutoConfigurationBase {

    // Make test passed option 1: Removing the following bean definition.
    @Bean
    @ConfigurationProperties(PropertyOne.PREFIX)
    PropertyOne propertyOne() {
        return new PropertyOne();
    }

    static class AutoConfigurationOneDemoBean {

    }
}
