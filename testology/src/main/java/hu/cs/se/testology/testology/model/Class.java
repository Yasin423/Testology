package hu.cs.se.testology.testology.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Class {

    @Column(name = "class_id")
    private long id;

    private String className;
    private String description;
    private String accessCode;

    private List<Test> tests;
    private List<User> students;
    private User teacher;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    @OneToMany(mappedBy = "aClass" , cascade = CascadeType.ALL)
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }


    @ManyToMany
    @JoinTable(name = "student_class" , joinColumns = @JoinColumn(name = "class_id") , inverseJoinColumns = @JoinColumn(name = "student_id"))
    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
