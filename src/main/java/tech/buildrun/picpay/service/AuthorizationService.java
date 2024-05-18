package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;
import tech.buildrun.picpay.client.AuthorizationClient;
import tech.buildrun.picpay.controller.dto.TransferDto;
import tech.buildrun.picpay.exception.PicPayException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {

        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
