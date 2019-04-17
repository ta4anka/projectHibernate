package controller;

import model.Skill;
import repository.SkillRepository;
import repository.impl.SkillRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class SkillController{
    SkillRepository skillRepository = new SkillRepositoryImpl();

    public void save(Skill skill){
        if(skill == null){
            throw new IllegalArgumentException();
        }else {
            skillRepository.save(skill);
        }
    }

    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    public void update(Skill skill){
        if(skill == null){
            throw  new IllegalArgumentException();
        }else skillRepository.update(skill);
    }

    public void delete(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            skillRepository.delete(id);
        }
    }

    public Skill getById(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return skillRepository.getById(id);
    }
}
