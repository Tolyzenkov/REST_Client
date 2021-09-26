package org.example;

import org.example.config.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Calendar;

/**
 * Hello world!
 *
 */
public class App {
    private static Long id = 3L;
    private static String name = "James";
    private static String lastName = "Brown";
    private static Byte age = 26;

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        User user = new User(id, name, lastName, age);
        System.out.println(communication.showAllUsers());
        communication.addUser(user);
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.editUser(user);
        communication.deleteUser(id);
    }
}
