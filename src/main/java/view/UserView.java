package view;

import controller.SkillController;
import controller.UserController;

import model.Skill;
import model.User;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserView {


    UserController userController = new UserController();
    SkillController skillController = new SkillController();
    static Scanner scanner = new Scanner(System.in);


    public void getUserMenu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter:" +"\n"+
                "'add' for adding new user" +"\n"+
                "'delete' for deleting user" +"\n"+
                "'show' for showing all the users"+"\n"+
                "'get' for for showing a certain user"+"\n"+
                "'edit' for change a certain user"+"\n"+
                "'menu' for return to main menu" + "\n" +
                "'exit' for exit");

        String input = scanner.next();
        User userToSave = new User();
        Integer id;

        while(!input.equals("exit")){
            switch(input){
                case "add":

                    System.out.println("Enter the first name:");
                    input = scanner.next();
                    userToSave.setFirstName(input);

                    System.out.println("Enter the last name:");
                    input = scanner.next();
                    userToSave.setLastName(input);

                    System.out.println("Enter the specilty:");
                    input = scanner.next();
                    userToSave.setSpecialty(input);

                    //--------Set of skills' logic---------------------------
                    System.out.println("Choose some skills from this list and enter its id by using ',' :");
                    List<Skill> skills = skillController.findAll();
                    for (Skill aSkill: skills) {
                        System.out.println(aSkill + " id= " +aSkill.getId());
                    }
                    scanner.nextLine();
                    input = scanner.nextLine();

                    Set<Skill> skillSet = new HashSet<>();
                    for(Integer sId: ConsoleHelper.inputToSetId(input)){
                        Skill skillToSave = skillController.getById(sId);
                        skillSet.add(skillToSave);
                    }
                    userToSave.setSkills(skillSet);
                    userController.save(userToSave);

                    System.out.println("New user was successfully added!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "delete":
                    System.out.println("Enter id of deleting user:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    userController.delete(id);
                    System.out.println("User was successfully deleted!");
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "show":
                    List<User> users = userController.findAll();
                    for (User aUser: users) {
                        System.out.println(aUser + " id= " +aUser.getId());
                    }

                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;
                case "get":
                    System.out.println("Enter id of user:");
                    input = scanner.next();
                    id = Integer.parseInt(input);
                    User user = userController.getById(id);
                    System.out.println(user);
                    System.out.println("What do you want to do now?");
                    input = scanner.next();
                    break;

                case "edit":
                    System.out.println("Enter id of updating user:");
                    input = scanner.next();
                    userToSave.setId(Integer.parseInt(input));

                    System.out.println("Enter the new first name:");
                    input = scanner.next();
                    userToSave.setFirstName(input);

                    System.out.println("Enter the new last name:");
                    input = scanner.next();
                    userToSave.setLastName(input);
                    System.out.println("Enter the specilty:");
                    input = scanner.next();
                    userToSave.setSpecialty(input);

                    userController.update(userToSave);
                    System.out.println("User was successfully changed!");
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