package tech.buildrun.picpay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.buildrun.picpay.controller.dto.CreateWalletDto;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.CustomerDataAlreadyExistsException;
import tech.buildrun.picpay.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void createWallet(CreateWalletDto wallet) {

        var dbWallet = walletRepository.findByCpfCnpjOrEmail(wallet.cpfCnpj(), wallet.email());
        if (dbWallet.isPresent()) {
            throw new CustomerDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        walletRepository.save(wallet.toWallet());
    }

    public Page<Wallet> findAll(PageRequest pageRequest) {
        return walletRepository.findAll(pageRequest);
    }
}
