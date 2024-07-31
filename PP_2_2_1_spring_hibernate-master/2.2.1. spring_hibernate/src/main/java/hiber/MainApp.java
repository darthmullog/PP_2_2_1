package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        userService.add(new User("User1", "Lastname1", "user1@mail.ru").setCar(new Car("BMW", 5)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru").setCar(new Car("Bentley", 2014)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());

            Car car = user.getCar();

            if (car != null) {
                System.out.println("Car Model = " + car.getModel());
                System.out.println("Car Series = " + car.getSeries());
            }
            System.out.println();
        }
        //ниже (строки 52-56)- проверка работы метода getUserByCar
        String model = "Bentley";
        int series = 2014;

        User user = userService.getUserByCar(model, series);
        System.out.println("имя владельца Bentley: " + user.toString());
        context.close();
    }
}
