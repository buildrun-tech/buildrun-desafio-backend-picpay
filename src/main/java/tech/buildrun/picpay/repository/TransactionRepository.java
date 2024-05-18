package tech.buildrun.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.picpay.entity.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
