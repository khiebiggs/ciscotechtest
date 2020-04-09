package app.DB;

import app.model.Average;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AverageDao {
    public void saveAverage(Average average) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(average);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
