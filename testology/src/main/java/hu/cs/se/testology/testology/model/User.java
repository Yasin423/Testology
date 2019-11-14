package hu.cs.se.testology.testology.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private List<ClassRegistration> registrations;
    private List<Class> classesAsTeacher;
    private List<TestResult> testResults;
    private List<QuestionAnswer> questionAnswers;

    //roles are stored comma separated
    private String role = "";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    public List<ClassRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<ClassRegistration> registrations) {
        this.registrations = registrations;
    }

    @OneToMany(mappedBy = "teacher" , cascade = CascadeType.ALL)
    public List<Class> getClassesAsTeacher() {
        return classesAsTeacher;
    }

    public void setClassesAsTeacher(List<Class> classesAsTeacher) {
        this.classesAsTeacher = classesAsTeacher;
    }

    @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL)
    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}
