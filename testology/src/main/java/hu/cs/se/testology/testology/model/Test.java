package hu.cs.se.testology.testology.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Test {

    @Column(name = "test_id")
    private long id;

    private String testName;

    private Class aClass;
    private List<TestResult> testResults;
    private List<Question> questions;

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


    @ManyToOne
    @JoinColumn(name = "class_id")
    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @OneToMany(mappedBy = "test")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @OneToMany(mappedBy = "test")
    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }

}
