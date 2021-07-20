package ru.geekbrains.myhiberspring.lesson6.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "sex")
    private boolean sex;

    @Column(name = "age")
    private Integer age;

    @OneToOne
    @JoinColumn(name = "security")
    private UserSecurty securityInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
            (
                    name = "cart",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn (name = "product_id")
            )
    private List<Product> products;

    public User(Long id,String name, String surname, boolean sex, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
    }

    public User(String name, String surname, boolean sex, Integer age) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}

