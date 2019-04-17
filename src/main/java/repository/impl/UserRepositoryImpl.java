package repository.impl;

import model.User;
import org.hibernate.Session;
import repository.UserRepository;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {


    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);
        session.getTransaction().commit();
        session.close();

    }

    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        List<User> users = session.createQuery(criteria).getResultList();
        session.close();
        return users;
    }

    public void update(User user) {
        int id = user.getId();
        String newFistName = user.getFirstName();
        String newLastName = user.getLastName();
        String newSpecialty = user.getSpecialty();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User userToUpdate = session.get(User.class,id);
        userToUpdate.setFirstName(newFistName);
        userToUpdate.setLastName(newLastName);
        userToUpdate.setSpecialty(newSpecialty);

        session.update(userToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User user = session.get(User.class,id);

        session.delete(user);
        session.getTransaction().commit();
        session.close();

    }

    public User getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class,id);
        session.close();
        return user;
    }
}

