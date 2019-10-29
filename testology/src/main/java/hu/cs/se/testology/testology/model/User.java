package hu.cs.se.testology.testology.model;

import javax.persistence.*;

@Entity
public class User {
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String lastName;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
