package ru.geekbrains.myhiberspring.lesson6.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.myhiberspring.lesson6.entities.Product;
import ru.geekbrains.myhiberspring.lesson6.entities.User;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

        public List<User> findUserByProductId(Long id){
            List<User> users = new ArrayList<>();
            try (Session session = sessionFactory.getCurrentSession()) {
                session.beginTransaction();
                users = session.get(Product.class,id).getUsers();
                session.getTransaction().commit();
            }
            return users;
        }

    }

