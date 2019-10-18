package hu.cs.se.testology.testology.model;

import javax.persistence.*;

@Entity
public class Test {

    @Column(name = "test_id")
    private long id;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
