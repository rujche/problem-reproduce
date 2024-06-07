package rujche.problem.reproduce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import rujche.problem.reproduce.customer.provided.ServiceBusJmsApp;

import static rujche.problem.reproduce.constant.QueueNameConstant.QUEUE_NAME_1;
import static rujche.problem.reproduce.constant.QueueNameConstant.QUEUE_NAME_2;

@Service
public class QueueReceiveThenSendService {
    private final Logger LOGGER = LoggerFactory.getLogger(QueueReceiveThenSendService.class);

    private final JmsTemplate jmsTemplate;
    private final ServiceBusJmsApp app;

    public QueueReceiveThenSendService(JmsTemplate jmsTemplate, ServiceBusJmsApp app) {
        this.jmsTemplate = jmsTemplate;
        this.app = app;
    }

    // @JmsListener(destination = QUEUE_NAME_1, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        LOGGER.info("Received message. queue name = {}, message = {}.", QUEUE_NAME_1, message);
        LOGGER.info("Sending message. queue name = {}, message = {}", QUEUE_NAME_2, message);
        jmsTemplate.convertAndSend(QUEUE_NAME_2, message);
        LOGGER.info("Send message succeed. queue name = {}, message = {}", QUEUE_NAME_2, message);
    }

    @JmsListener(destination = QUEUE_NAME_1, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessageByServiceBusJmsApp(String message) {
        LOGGER.info(" - ServiceBusJmsApp - Received message. queue name = {}, message = {}.", QUEUE_NAME_1, message);
        LOGGER.info(" - ServiceBusJmsApp - Sending message. queue name = {}, message = {}", QUEUE_NAME_2, message);
        app.sendMessage(QUEUE_NAME_2, message);
        LOGGER.info(" - ServiceBusJmsApp - Send message succeed. queue name = {}, message = {}", QUEUE_NAME_2, message);
    }
}
