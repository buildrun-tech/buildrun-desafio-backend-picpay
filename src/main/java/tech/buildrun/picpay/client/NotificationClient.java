package tech.buildrun.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.buildrun.picpay.entity.Wallet;

@FeignClient(
        name = "NotificationClient",
        url = "${client.notification.url}"
)
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> notifyReceiver(@RequestBody Wallet receiver);
}
