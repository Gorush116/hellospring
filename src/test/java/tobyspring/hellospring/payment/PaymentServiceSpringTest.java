package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

// Spring 기능을 사용하기 위해 사용하는 어노테이션
@SuppressWarnings("ALL")
@ExtendWith(SpringExtension.class)
// 설정 정보가 담긴 class를 가져온다
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    // 이 어노테이션을 통해 컨테이너 안의 오브젝트를 주입(@ExtendWith(SpringExtension.class) 필요)
    @Autowired
    PaymentService paymentService;

    @Autowired
    Clock clock;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        // exRate : 1000
        // 실행
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        // 환율정보 가져온다
        // BigDecimal 의 값 비교시 isEqualTo 보다 isEqualByComparingTo가 권장됨
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));


        // exRate : 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 검증
        // 환율정보 가져온다
        // BigDecimal 의 값 비교시 isEqualTo 보다 isEqualByComparingTo가 권장됨
        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        // 원화환산금액 계산
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));

    }

    @Test
    void validUntil() throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // valid until이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

}