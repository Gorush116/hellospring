package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {

        getPayment(valueOf(500), valueOf(5_000));
        getPayment(valueOf(1_000), valueOf(10_000));
        getPayment(valueOf(3_000), valueOf(30_000));

        // 원화환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));


    }

    private static void getPayment(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        // 준비
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        // 실행
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        // 환율정보 가져온다
        // BigDecimal 의 값 비교시 isEqualTo 보다 isEqualByComparingTo가 권장됨
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}