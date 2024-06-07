package rujche.problem.reproduce.customer.provided;

import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.spring.cloud.autoconfigure.implementation.jms.properties.AzureServiceBusJmsProperties;
import com.azure.spring.cloud.core.implementation.connectionstring.ServiceBusConnectionString;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Session;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Map;

import static rujche.problem.reproduce.constant.AzureADConstant.AZURE_AD_TOKEN_USERNAME;
import static rujche.problem.reproduce.constant.AzureADConstant.SERVICE_BUD_DEFAULT_SCOPE;

@Service
public class ServiceBusJmsApp {

    //To create the JmsTemplate we are using method: jmsTemplate(AzureServiceBusJmsProperties busJMSProperties) (defined below)
    private final DefaultAzureCredential credential;
    private final JmsTemplate jmsTemplate;

    public ServiceBusJmsApp(AzureServiceBusJmsProperties properties) {
        this.credential = new DefaultAzureCredentialBuilder().build();
        this.jmsTemplate = jmsTemplate(properties);
    }

    public void sendMessage(String destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(String json, Map<String, String> properties, String instanceId) {
        jmsTemplate.convertAndSend("hip.fwk.process.int.opsfin-01-odes-quick.2", "{}", jmsMessage -> {
            if (properties != null) {
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    jmsMessage.setStringProperty(entry.getKey(), entry.getValue());
                }
            }
            return jmsMessage;
        });
    }

    //YAML config:
    /*
    spring:
      jms:
        servicebus:
          connection-string: ****
          idle-timeout: 1800000
          pricing-tier: PREMIUM
          prefetch-policy:
            all: 1
     */
    public JmsTemplate jmsTemplate(AzureServiceBusJmsProperties busJMSProperties) {

        CachingConnectionFactory factory = buildAzureConnectionFactoryForProducer(busJMSProperties, 100, 60000);
        JmsTemplate jmsTemplate = new JmsTemplate(factory);

        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        jmsTemplate.setMessageConverter(new CustomMessageConverter());
        jmsTemplate.setSessionTransacted(false);

        return jmsTemplate;
    }

    public CachingConnectionFactory buildAzureConnectionFactoryForProducer(AzureServiceBusJmsProperties busJMSProperties,
                                                                                  int cacheSize, long requestTimeout) {
        final JmsConnectionFactory jmsConnectionFactory = getJmsConnectionFactory(busJMSProperties, requestTimeout);

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(jmsConnectionFactory);
        cachingConnectionFactory.setSessionCacheSize(cacheSize);
        cachingConnectionFactory.setCacheProducers(true);
        cachingConnectionFactory.setCacheConsumers(false);
        return cachingConnectionFactory;
    }

    private JmsConnectionFactory getJmsConnectionFactory(AzureServiceBusJmsProperties properties, long requestTimeout) {
        boolean connectionStringConfigured = StringUtils.hasText(properties.getConnectionString());
        // I (rujche) add this logic because I can't use connection-string now.
        // It should work if you use connection-string. But I didn't test it.
        if (connectionStringConfigured) {
            return createJmsConnectionFactoryByConnectionString(properties, requestTimeout);
        } else {
            return createJmsConnectionFactoryByNamespace(properties, requestTimeout);
        }
    }

    private JmsConnectionFactory createJmsConnectionFactoryByConnectionString(AzureServiceBusJmsProperties properties, long requestTimeout) {
        ServiceBusConnectionString serviceBusConnectionString = new ServiceBusConnectionString(properties.getConnectionString());
        String username = serviceBusConnectionString.getSharedAccessKeyName();
        String password = serviceBusConnectionString.getSharedAccessKey();
        String host = serviceBusConnectionString.getEndpointUri().getHost();
        Duration idleTimeout = properties.getIdleTimeout();
        String remoteUri = String.format("amqps://%s?amqp.idleTimeout=%d&amqp.traceFrames=true", host, idleTimeout.toMillis());
        JmsConnectionFactory connectionFactory = new JmsConnectionFactory(username, password, remoteUri);
        connectionFactory.setRequestTimeout(requestTimeout);
        return connectionFactory;
    }

    private JmsConnectionFactory createJmsConnectionFactoryByNamespace(AzureServiceBusJmsProperties properties, long requestTimeout) {
        Duration idleTimeout = properties.getIdleTimeout();
        String remoteUri = String.format("amqps://%s?amqp.idleTimeout=%d&amqp.traceFrames=true",
                properties.getNamespace() + "." + properties.getProfile().getEnvironment().getServiceBusDomainName(),
                idleTimeout.toMillis());
        String password = credential.getToken(new TokenRequestContext().addScopes(SERVICE_BUD_DEFAULT_SCOPE))
                                    .block()
                                    .getToken();;
        JmsConnectionFactory connectionFactory = new JmsConnectionFactory(AZURE_AD_TOKEN_USERNAME, password, remoteUri);
        connectionFactory.setRequestTimeout(requestTimeout);
        return connectionFactory;
    }

    static class CustomMessageConverter extends MappingJackson2MessageConverter {

        public CustomMessageConverter() {
            this.setTargetType(MessageType.BYTES);
            this.setTypeIdPropertyName("_type");
        }

    }
}
