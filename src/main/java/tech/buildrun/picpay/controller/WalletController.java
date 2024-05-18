package tech.buildrun.picpay.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.picpay.controller.dto.CreateWalletDto;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.service.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Void> createWallet(@RequestBody CreateWalletDto createWalletDto) {
        walletService.createWallet(createWalletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<Wallet>> listWallets(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var response = walletService.findAll(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(response);
    }
}
