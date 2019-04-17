import model.Skill;
import org.hibernate.Session;
import repository.SkillRepository;
import repository.impl.SkillRepositoryImpl;
import util.HibernateUtil;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        SkillRepository sr = new SkillRepositoryImpl();
//        Skill aSkill = sr.getById(1);
       /* List<Skill> skills = sr.findAll();
        for (Skill s: skills
             ) {
            System.out.println(s);

        }*/
//       sr.delete(14);
//       sr.delete(13);
//       sr.delete(12);
        Skill aSkill = sr.getById(13);
        aSkill.setName("april");
        sr.update(aSkill);
        Skill s= new Skill("march");
//        sr.save(s);
        sr.delete(14);






        }

    }

