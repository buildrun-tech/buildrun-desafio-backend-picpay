package tech.buildrun.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceException extends PicPayException {

    private BigDecimal currentBalance;

    public InsufficientBalanceException(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer declined.");
        pb.setDetail("Your current balance is " + currentBalance + ".");

        return pb;
    }
}
