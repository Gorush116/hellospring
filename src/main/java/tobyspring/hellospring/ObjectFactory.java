package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 구성정보를 포함하는 클래스임을 어노테이션을 통해 명시
@Configuration
// 컴포넌트 탐색 및 범위를 설정하는 어노테이션
@ComponentScan
public class ObjectFactory {

/*  주석시 NoSuchBeanDefinitionException 발생(No qualifying bean of type 'tobyspring.hellospring.PaymentService' available)
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    // Bean을 생성하는 메소드임을 명시
    @Bean
    public ExRateProvider exRateProvider() {
//        return new WebApiExRateProvider();
        return new SimpleExRateProvider();
    }*/
}
