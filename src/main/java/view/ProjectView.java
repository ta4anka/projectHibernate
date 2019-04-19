package view;

import controller.TeamController;
import controller.ProjectController;

import model.Project;
import model.Team;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ProjectView {


    ProjectController ProjectController = new ProjectController();
    TeamController TeamController = new TeamController();
    static Scanner scanner = new Scanner(System.in);


    public void getProjectMenu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new Project" +"\n"+
                "'delete' for deleting Project" +"\n"+
                "'show' for showing all the Projects"+"\n"+
                "'get' for for showing a certain Project"+"\n"+
                "'edit' for change a certain Project"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        Project projectToSave = new Project();
        int id;
        BigDecimal budget;

        while(!input.equals("exit")){
            switch(input){
                case "add":

                    System.out.println("Enter the name:");
                    input = scanner.next();
                    projectToSave.setName(input);

                    System.out.println("Enter the budget:");
                    input = scanner.next();
                    budget = new BigDecimal(input);
                    projectToSave.setBudget(budget);



                    //--------Set of Teams' logic---------------------------
                    System.out.println("Choose some teams from this list and enter its id by using ',' :");
                    List<Team> teams = TeamController.findAll();
                    for (Team aTeam: teams) {
                        System.out.println(aTeam + " id= " +aTeam.getId());
                    }
                    scanner.nextLine();
                    input = scanner.nextLine();

                    Set<Team> teamSet = new HashSet<>();
                    for(Integer sId: ConsoleHelper.inputToSetId(input)){
                        Team TeamToSave = TeamController.getById(sId);
                        teamSet.add(TeamToSave);
                    }
                    projectToSave.setTeams(teamSet);
                    ProjectController.save(projectToSave);

                    System.out.println("New Project was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting Project:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    ProjectController.delete(id);
                    System.out.println("Project was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    List<Project> Projects = ProjectController.findAll();
                    for (Project aProject: Projects) {
                        System.out.println(aProject + " id= " +aProject.getId());
                    }

                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "get":
                    System.out.println("Enter id of project:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    Project project = ProjectController.getById(id);
                    System.out.println(project);
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "edit":
                    System.out.println("Enter id of updating Project:");
                    input = scanner.next();
                    projectToSave.setId(Integer.parseInt(input));

                    System.out.println("Enter the new name:");
                    input = scanner.next();
                    projectToSave.setName(input);

                    System.out.println("Enter the new budget:");
                    input = scanner.next();
                    budget = new BigDecimal(input);
                    projectToSave.setBudget(budget);
                    
                    ProjectController.update(projectToSave);
                    System.out.println("Project was successfully changed!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "menu":
                    ConsoleHelper ch = new ConsoleHelper();
                    ch.getMenu();

                default:
                    System.out.println("Please,make your choice!");
                    input = scanner.next();
            }
        }

    }
}