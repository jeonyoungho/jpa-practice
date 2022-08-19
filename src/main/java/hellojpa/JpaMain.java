package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Notification notification = new Notification("test");
            em.persist(notification);

            ScheduleInfo scheduleInfo = new ScheduleInfo(notification);
            em.persist(scheduleInfo);

            em.flush();
            em.clear();

            System.out.println("=====");

            ScheduleInfo findScheduleInfo = em.find(ScheduleInfo.class, scheduleInfo.getId());

            System.out.println("======");

            Notification findNotification = em.find(Notification.class, notification.getId());

            em.remove(findNotification);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            //close
            em.close();
        }

        emf.close();
    }
}
