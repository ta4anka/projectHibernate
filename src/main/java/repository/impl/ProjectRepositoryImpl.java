package repository.impl;

import model.Project;

import org.hibernate.Session;
import repository.ProjectRepository;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    public void save(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(project);
        session.getTransaction().commit();
        session.close();

    }

    public List<Project> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        Root<Project> root = criteria.from(Project.class);
        criteria.select(root);

        List<Project> projects = session.createQuery(criteria).getResultList();
        session.close();
        return projects;
    }

    public void update(Project project) {
        int id = project.getId();
        String newName = project.getName();
        BigDecimal newBudget = project.getBudget();

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Project projectToUpdate = session.get(Project.class,id);
        projectToUpdate.setName(newName);
        projectToUpdate.setBudget(newBudget);

        session.update(projectToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Project project = session.get(Project.class,id);

        session.delete(project);
        session.getTransaction().commit();
        session.close();

    }

    public Project getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project = session.get(Project.class,id);
        session.close();
        return project;
    }
}

