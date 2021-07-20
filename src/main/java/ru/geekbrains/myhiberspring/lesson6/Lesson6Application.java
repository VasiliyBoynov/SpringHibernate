package ru.geekbrains.myhiberspring.lesson6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.myhiberspring.lesson6.entities.Product;
import ru.geekbrains.myhiberspring.lesson6.repositories.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class Lesson6Application {

    private static UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Lesson6Application.class, args);
        //System.out.println(userRepository.readID(1L));
        //System.out.println(userRepository.readID(1L).getSecurityInfo());
        //System.out.println("--------------------------");
        //userRepository.creatUser(1L,"testName","testSurname",25,true);
        //System.out.println("--------------------------");
        //System.out.println(userRepository.raedAll());
        //System.out.println("--------------------------");
        //userRepository.deleteUserID(1L);
        //System.out.println("--------------------------");
        //System.out.println(userRepository.raedAll());
        System.out.println("--------------------------");
        List<Product> products = userRepository.findProductForUser(1L);
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("--------------------------");
    }

}
