package rujche.problem.reproduce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import rujche.problem.reproduce.configuration.AutoConfigurationOne;
import rujche.problem.reproduce.configuration.AutoConfigurationTwo;
import rujche.problem.reproduce.property.PropertyTwo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoConfigurationTests {

    @Test
    void testPropertyBindingByApplicationContextRunnerWithOneAutoConfiguration() { // Test passed
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(
                        //	AutoConfigurationOne.class,
                        AutoConfigurationTwo.class))
                .withPropertyValues("property-two.value=property-two-value")
                .run(context -> {
                    assertThat(context).hasSingleBean(PropertyTwo.class);
                    PropertyTwo two = context.getBean(PropertyTwo.class);
                    assertEquals("property-two-value", two.getValue());
                });
    }

    @Test
    void testPropertyBindingByApplicationContextRunnerWithTwoAutoConfigurations() { // Test failed
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(
                        AutoConfigurationOne.class,  // Difference here.
                        AutoConfigurationTwo.class))
                .withPropertyValues("property-two.value=property-two-value")
                .run(context -> {
                    assertThat(context).hasSingleBean(PropertyTwo.class);
                    PropertyTwo two = context.getBean(PropertyTwo.class);
                    assertEquals("property-two-value", two.getValue());
                });
    }
}
