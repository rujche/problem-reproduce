package rujche.spring.cloud.azure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringCloudAzureSampleBean {
    public static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudAzureSampleBean.class);
    private final SpringCloudAzureProperties properties;

    public SpringCloudAzureSampleBean(SpringCloudAzureProperties properties) {
        this.properties = properties;
        printProperties();
    }

    public void printProperties() {
        LOGGER.info("properties.subscription = {}", properties.getSubscriptionId());
        LOGGER.info("properties.resource-group = {}", properties.getResourceGroup());
    }
}
