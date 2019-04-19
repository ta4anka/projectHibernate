package repository.impl;



import model.Team;
import org.hibernate.Session;
import repository.TeamRepository;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {

    public void save(Team team) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(team);
        session.getTransaction().commit();
        session.close();

    }

    public List<Team> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Team> criteria = builder.createQuery(Team.class);
        Root<Team> root = criteria.from(Team.class);
        criteria.select(root);

        List<Team> Teams = session.createQuery(criteria).getResultList();
        session.close();
        return Teams;
    }

    public void update(Team team) {
        int id = team.getId();
        String newName = team.getName();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Team teamToUpdate = session.get(Team.class,id);
        teamToUpdate.setName(newName);

        session.update(teamToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Team team = session.get(Team.class,id);

        session.delete(team);
        session.getTransaction().commit();
        session.close();

    }

    public Team getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Team team = session.get(Team.class,id);
        session.close();
        return team;
    }
}
