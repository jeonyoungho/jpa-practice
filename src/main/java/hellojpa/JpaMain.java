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
            Notification notificationWithSchedule = new Notification("create-with-schedule");
            em.persist(notificationWithSchedule);

            ScheduleInfo scheduleInfo = new ScheduleInfo(notificationWithSchedule);
            em.persist(scheduleInfo);

            em.flush();
            em.clear();

            System.out.println("===========================================================");

            ScheduleInfo findScheduleInfo = em.find(ScheduleInfo.class, scheduleInfo.getId());

            System.out.println("===========================================================");

            Notification findNotification = em.find(Notification.class, notificationWithSchedule.getId());

            em.remove(findNotification);

            em.flush();
            em.clear();

            System.out.println("===========================================================");

            Notification notificationWithoutSchedule = new Notification("create-without-schedule");
            em.persist(notificationWithoutSchedule);

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
