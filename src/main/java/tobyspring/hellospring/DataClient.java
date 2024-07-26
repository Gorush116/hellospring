package tobyspring.hellospring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
        
        // entity manager
        EntityManager em = emf.createEntityManager();

        // transaction
        em.getTransaction().begin();

        Order order = new Order("100", BigDecimal.TEN);

        // em.persist 영속화 해달라 -> 작업 이후에도 그 변경사항이 남아있도록 처리
        em.persist(order); // 새로 만든 Object를 DB에 삽입

        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }
}
