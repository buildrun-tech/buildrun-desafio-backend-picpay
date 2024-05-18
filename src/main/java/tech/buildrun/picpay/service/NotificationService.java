package tech.buildrun.picpay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.buildrun.picpay.client.NotificationClient;
import tech.buildrun.picpay.entity.Transaction;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void notifyTransaction(Transaction transaction) {
        try {
            logger.info("Sending notification...");
            var response = notificationClient.notifyReceiver(transaction.getReceiver());

            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                logger.error("Error sending notification, status code is not 200 OK");
            }

        } catch (Exception e) {
            logger.error("Error sending notification", e);
        }
    }
}
