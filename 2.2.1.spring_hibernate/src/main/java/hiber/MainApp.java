package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car = new Car("Model1", 1);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car);
      car.setUser(user1);
      userService.add(user1);
      carService.add(car);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+ user.getId() +
                 " First Name = " + user.getFirstName() +
                 " Last name = " + user.getLastName() +
                 " Email = " + user.getEmail() +
                 " Car = " + user.getCar());
      }

      System.out.println(userService.findUserByCarModelAndSeries("Model1", 1));

      context.close();
   }
}
