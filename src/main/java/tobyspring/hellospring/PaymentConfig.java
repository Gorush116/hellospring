package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

// 구성정보를 포함하는 클래스임을 어노테이션을 통해 명시
// @Configuration을 통해 new 로 새로운 Object를 선언하여도 Proxy 를 통해 같은 Object를 사용하게끔 구현
@Configuration
// 컴포넌트 탐색 및 범위를 설정하는 어노테이션
// @ComponentScan
public class PaymentConfig {

  // 주석시 NoSuchBeanDefinitionException 발생(No qualifying bean of type 'tobyspring.hellospring.payment.PaymentService' available)
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
//        return new PaymentService(cachedExRateProvider());
    }
    // Bean을 생성하는 메소드임을 명시
    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
//        return new SimpleExRateProvider();
    }

    // 캐시 Bean 생성
    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
