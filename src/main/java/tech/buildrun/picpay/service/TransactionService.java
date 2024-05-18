package tech.buildrun.picpay.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.buildrun.picpay.controller.dto.TransferDto;
import tech.buildrun.picpay.entity.Transaction;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.InsufficientBalanceException;
import tech.buildrun.picpay.exception.TransferNotAuthorizedException;
import tech.buildrun.picpay.exception.WalletNotFound;
import tech.buildrun.picpay.exception.TransferNotAllowedException;
import tech.buildrun.picpay.repository.TransactionRepository;
import tech.buildrun.picpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository,
                              WalletRepository walletRepository,
                              AuthorizationService authorizationService,
                              NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional
    public void transfer(TransferDto transfer) {

        var sender = walletRepository.findById(transfer.payer())
                .orElseThrow(() -> new WalletNotFound(transfer.payee()));

        var receiver = walletRepository.findById(transfer.payee())
                .orElseThrow(() -> new WalletNotFound(transfer.payee()));

        validateTransfer(transfer, sender);

        sender.debit(transfer.value());
        receiver.credit(transfer.value());

        var transaction = new Transaction(sender, receiver, transfer.value());

        transactionRepository.save(transaction);
        walletRepository.save(sender);
        walletRepository.save(receiver);

        CompletableFuture.runAsync(() -> notificationService.notifyTransaction(transaction));
    }

    private void validateTransfer(TransferDto transfer, Wallet sender) {
        if (!sender.isTransferAllowed()) {
            throw new TransferNotAllowedException();
        }

        if (!sender.isBalanceBiggerThan(transfer.value())) {
            throw new InsufficientBalanceException(sender.getBalance());
        }

        if (!authorizationService.isTransferAllowed(transfer)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
