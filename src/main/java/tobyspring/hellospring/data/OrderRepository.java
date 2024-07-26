package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import tobyspring.hellospring.order.Order;

public class OrderRepository {

    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {
        // entity manager
        EntityManager em = emf.createEntityManager();

        // transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // em.persist 영속화 해달라 -> 작업 이후에도 그 변경사항이 남아있도록 처리
        try {
            em.persist(order); // 새로 만든 Object를 DB에 삽입
            em.flush();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback(); // transaction이 active라면
            throw e;
        } finally {
            if (em.isOpen()) em.close();    // em이 열려 있다면
        }

    }
}
