package tech.buildrun.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
