package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.ExRateProviderStub;
import tobyspring.hellospring.payment.PaymentService;

import java.math.BigDecimal;

// 구성정보를 포함하는 클래스임을 어노테이션을 통해 명시
// @Configuration을 통해 new 로 새로운 Object를 선언하여도 Proxy 를 통해 같은 Object를 사용하게끔 구현
@Configuration
// 컴포넌트 탐색 및 범위를 설정하는 어노테이션
// @ComponentScan
public class TestObjectFactory {

  // 주석시 NoSuchBeanDefinitionException 발생(No qualifying bean of type 'tobyspring.hellospring.payment.PaymentService' available)
    @Bean
    public PaymentService paymentService() {
//        return new PaymentService(exRateProvider());
        return new PaymentService(exRateProvider());
    }
    // Bean을 생성하는 메소드임을 명시
    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
