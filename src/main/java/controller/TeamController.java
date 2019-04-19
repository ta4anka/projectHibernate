package controller;

import model.Team;
import repository.TeamRepository;
import repository.impl.TeamRepositoryImpl;


import java.util.List;

public class TeamController{

    TeamRepository teamRepository = new TeamRepositoryImpl();

    public void save(Team team){
        if(team == null){
            throw new IllegalArgumentException();
        }else {
            teamRepository.save(team);
        }

    }


    public List<Team> findAll(){
        return teamRepository.findAll();
    }


    public void update(Team team){
        if(team== null){
            throw  new IllegalArgumentException();
        }else teamRepository.update(team);

    }


    public void delete(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            teamRepository.delete(id);
        }

    }


    public Team getById(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return teamRepository.getById(id);
    }
}
