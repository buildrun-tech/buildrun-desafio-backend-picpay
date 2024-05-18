package tech.buildrun.picpay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.buildrun.picpay.client.AuthorizationClient;
import tech.buildrun.picpay.controller.dto.TransferDto;

@Service
public class AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

    private AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isTransferAllowed(TransferDto transaction) {
        logger.info("Checking authorization service...");
        return authorizationClient.getAuthorization().transferAllowed();
    }

}
