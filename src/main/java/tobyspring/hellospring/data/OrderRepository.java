package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;
import tobyspring.hellospring.order.Order;

public class OrderRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order); // 새로 만든 Object를 DB에 삽입
    }
}
