package tech.buildrun.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tech.buildrun.picpay.client.dto.AuthorizationResponse;

@FeignClient(
        name = "AuthorizationClient",
        url = "${client.authorization.url}"
)
public interface AuthorizationClient {

    @GetMapping
    AuthorizationResponse getAuthorization();
}
