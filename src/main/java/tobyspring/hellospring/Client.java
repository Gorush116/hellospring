package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        // BeanFactory 통해 Bean 가져오기
//        PaymentService paymentService2 = beanFactory.getBean(PaymentService.class);
//        System.out.println("paymentService = " + paymentService);       // paymentService = tobyspring.hellospring.PaymentService@1e04fa0a
//        System.out.println("paymentService2 = " + paymentService2);     // paymentService2 = tobyspring.hellospring.PaymentService@1e04fa0a
//        System.out.println(paymentService == paymentService2);          // true

        // ObjectFactory 통해 Bean 가져오기
//        ObjectFactory objectFactory = beanFactory.getBean(ObjectFactory.class);
//        PaymentService paymentService1 = objectFactory.paymentService();
//        PaymentService paymentService2 = objectFactory.paymentService();
//        System.out.println("paymentService1 = " + paymentService1);     // paymentService1 = tobyspring.hellospring.PaymentService@6a84a97d
//        System.out.println("paymentService2 = " + paymentService2);     // paymentService2 = tobyspring.hellospring.PaymentService@6a84a97d
//        System.out.println(paymentService == paymentService2);          // true

//        OrderService orderService = beanFactory.getBean(OrderService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
