package rujche.spring.cloud.azure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@ConditionalOnProperty("spring.cloud.azure.enabled")
@EnableConfigurationProperties
@Import(SpringCloudAzureConnectionDetailConfiguration.class)
@AutoConfigureAfter(SpringCloudAzureConnectionDetailConfiguration.class)
public class SpringCloudAzureAutoConfiguration {

    @Bean
    @ConfigurationProperties(SpringCloudAzureProperties.PREFIX)
    SpringCloudAzureProperties azureEventHubsProperties() {
        return new SpringCloudAzureProperties();
    }

    @Bean
    SpringCloudAzureSampleBean springCloudAzureSampleBean(SpringCloudAzureProperties properties) {
        return new SpringCloudAzureSampleBean(properties);
    }

}
