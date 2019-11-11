package hu.cs.se.testology.testology.model;

import javax.persistence.*;

@Entity
public class Test {

    @Column(name = "test_id")
    private long id;

    private String testName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
