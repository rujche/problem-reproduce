package rujche.spring.cloud.azure;

import org.springframework.boot.autoconfigure.service.connection.ConnectionDetails;

public interface SpringCloudAzureConnectionDetails extends ConnectionDetails {
    public String getSubscriptionId();
    public String getResourceGroup();
}
