package tech.buildrun.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFound extends PicPayException {

    private Long walletId;

    public WalletNotFound(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Wallet not found.");
        pb.setDetail("There is no Wallet with id "+ walletId + ".");

        return pb;
    }
}
