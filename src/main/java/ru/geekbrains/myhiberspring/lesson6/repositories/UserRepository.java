package ru.geekbrains.myhiberspring.lesson6.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.myhiberspring.lesson6.entities.Product;
import ru.geekbrains.myhiberspring.lesson6.entities.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);

    }

    //Crud methods
    public List<User> raedAll() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("select s from User s  ").getResultList();
            for (User user : users) {
                System.out.println(user);
            }
            session.getTransaction().commit();
        }
        return users;
    }

    public User readID(Long id){
        User user = new User();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            user = session.get(User.class,id);
            session.getTransaction().commit();
        } finally {
            return  user;
        }
    }
    public void creatUser(String name, String surname, int age, boolean sex){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = new User(name,surname,sex,age);
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUserID(Long id){
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    public void updateUser(Long id,String name, String surname, int age, boolean sex){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class,id);
            user.setName(name);
            user.setSurname(surname);
            user.setAge(age);
            user.setSex(sex);
            session.getTransaction().commit();
        }
    }

    public List<Product> findProductForUser(Long id){
        List<Product> products = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            products = session.get(User.class,id).getProducts();
            //User user = session.get(User.class,id);
            //products = session.get(User.class,id).getProducts();
            session.getTransaction().commit();
        }
        return products;
    }




    }



