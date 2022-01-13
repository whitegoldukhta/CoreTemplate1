package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;

public class Main {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 5);
        userService.saveUser("Andrey", "Donskih", (byte) 64);
        userService.saveUser("Vladislav", "Nikolayev", (byte) 22);
        userService.saveUser("Ilia", "Grishin", (byte) 37);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }//end main
}//end Main

