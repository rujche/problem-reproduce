package rujche.spring.cloud.azure;

public class SpringCloudAzureProperties {
    public static final String PREFIX = "spring.cloud.azure";

    private String enabled;
    private String subscriptionId;
    private String resourceGroup;

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
