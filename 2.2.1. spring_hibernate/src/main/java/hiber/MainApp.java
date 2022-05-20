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

      Car car1 = new Car("Mazda", 6);
      Car car2 = new Car("BMW", 3);
      Car car3 = new Car("MB", 450);

      userService.add(new User("Petr", "Ivanov", "user1@mail.ru", car1));
      userService.add(new User("Ivan", "Sidorov", "user2@mail.ru", car2));
      userService.add(new User("Sergey", "Petrov", "user3@mail.ru", car3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      User user = userService.getUserByCar("BMW", 3);
      System.out.println(user);

      context.close();
   }
}
