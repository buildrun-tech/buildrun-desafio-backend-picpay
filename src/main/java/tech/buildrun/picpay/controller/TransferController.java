package tech.buildrun.picpay.controller;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.picpay.controller.dto.TransferDto;
import tech.buildrun.picpay.entity.Transfer;
import tech.buildrun.picpay.service.TransferService;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto) {
        return ResponseEntity.ok(transferService.transfer(dto));
    }

    @GetMapping("/transfer/{uuid}")
    public ResponseEntity<Transfer> getTransfer( UUID uuid) {
        return ResponseEntity.ok(transferService.getTransfer(uuid));
    }

    @GetMapping("/transfer")
    public ResponseEntity<List<Transfer>> getListTransfer() {
        return ResponseEntity.ok(transferService.getListTransfer());
    }
}
