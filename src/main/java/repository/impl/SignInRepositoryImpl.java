package repository.impl;

import model.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.SignInRepository;
import util.HibernateUtil;

public class SignInRepositoryImpl implements SignInRepository {
    @Override
    public User searchUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("FROM User WHERE email = :userEmail", User.class);
        return query.setParameter("userEmail", email).uniqueResult();
    }
}
