package rujche.problem.reproduce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

import static rujche.problem.reproduce.QueueName.QUEUE_NAME_1;

@Service
public class PeriodMessageSender {

    private final JmsTemplate jmsTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(PeriodMessageSender.class);

    public PeriodMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        new Thread(this::sendMessagePeriodically).start();
    }

    private void sendMessagePeriodically() {
        while(true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String message = new Date().toString();
            LOGGER.info(" == Send message periodically. queue name = {}, message = {}", QUEUE_NAME_1, message);
            jmsTemplate.convertAndSend(QUEUE_NAME_1, message);
        }
    }
}
