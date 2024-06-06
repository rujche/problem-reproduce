package rujche.problem.reproduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueReceiveThenSendService {
    private static final String RECEIVE_QUEUE_NAME = "que001";
    private static final String SEND_QUEUE_NAME = "que002";
    private final Logger LOGGER = LoggerFactory.getLogger(QueueReceiveThenSendService.class);

    private final JmsTemplate jmsTemplate;

    public QueueReceiveThenSendService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = RECEIVE_QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        LOGGER.info("Received message. queue name = {}, message = {}.", RECEIVE_QUEUE_NAME, message);
        LOGGER.info("Sending message. queue name = {}, message = {}", SEND_QUEUE_NAME, message);
        jmsTemplate.convertAndSend(SEND_QUEUE_NAME, message);
        LOGGER.info("Send message succeed. queue name = {}, message = {}", SEND_QUEUE_NAME, message);
    }
}
