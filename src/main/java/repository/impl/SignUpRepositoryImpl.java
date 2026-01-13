package repository.impl;

import model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.SignUpRepository;
import util.HibernateUtil;

public class SignUpRepositoryImpl implements SignUpRepository {

    @Override
    public boolean save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();

            return true;

        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            return false;

        } finally {
            session.close();
        }
    }
}
