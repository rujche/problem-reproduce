package rujche.problem.reproduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static rujche.problem.reproduce.QueueName.QUEUE_NAME_1;
import static rujche.problem.reproduce.QueueName.QUEUE_NAME_2;

@Service
public class QueueReceiveThenSendService {
    private final Logger LOGGER = LoggerFactory.getLogger(QueueReceiveThenSendService.class);

    private final JmsTemplate jmsTemplate;

    public QueueReceiveThenSendService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = QUEUE_NAME_1, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        LOGGER.info("Received message. queue name = {}, message = {}.", QUEUE_NAME_1, message);
        LOGGER.info("Sending message. queue name = {}, message = {}", QUEUE_NAME_2, message);
        jmsTemplate.convertAndSend(QUEUE_NAME_2, message);
        LOGGER.info("Send message succeed. queue name = {}, message = {}", QUEUE_NAME_2, message);
    }
}
