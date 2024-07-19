package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.ObjectFactory;
import tobyspring.hellospring.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

// Spring 기능을 사용하기 위해 사용하는 어노테이션
@SuppressWarnings("ALL")
@ExtendWith(SpringExtension.class)
// 설정 정보가 담긴 class를 가져온다
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {

    // 이 어노테이션을 통해 컨테이너 안의 오브젝트를 주입(@ExtendWith(SpringExtension.class) 필요)
    @Autowired
    PaymentService paymentService;

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

        // 원화환산금액의 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }

}