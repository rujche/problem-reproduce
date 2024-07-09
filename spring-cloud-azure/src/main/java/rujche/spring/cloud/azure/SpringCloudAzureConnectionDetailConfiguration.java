package rujche.spring.cloud.azure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

@Configuration
@ConditionalOnClass(ConnectionDetails.class)
@ConditionalOnBean(SpringCloudAzureConnectionDetails.class)
public class SpringCloudAzureConnectionDetailConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudAzureConnectionDetailConfiguration.class);

    public SpringCloudAzureConnectionDetailConfiguration(ConfigurableEnvironment  environment, SpringCloudAzureConnectionDetails detail) {
        LOGGER.info("Spring Cloud Azure Connection Detail Configuration");
        LOGGER.info("detail.getSubscriptionId() ");
        Properties properties = new Properties();
        properties.put("spring.cloud.azure.enabled", true);
        properties.put("spring.cloud.azure.subscription-id", detail.getSubscriptionId());
        properties.put("spring.cloud.azure.resource-group", detail.getResourceGroup());
        environment.getPropertySources().addFirst(new PropertiesPropertySource("SpringCloudAzureConnectionDetailConfiguration", properties));
    }
}
