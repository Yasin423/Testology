package hu.cs.se.testology.testology.model;

import javax.persistence.*;
import java.security.AllPermission;
import java.util.List;

@Entity
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

//    private List<Class> classesAsStudent;
    private List<ClassRegistration> registrations;

    private List<Class> classesAsTeacher;

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

    //    @ManyToMany(mappedBy = "students" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
//    public List<Class> getClassesAsStudent() {
//        return classesAsStudent;
//    }
//
//    public void setClassesAsStudent(List<Class> classesAsStudent) {
//        this.classesAsStudent = classesAsStudent;
//    }

    @OneToMany(mappedBy = "teacher" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    public List<Class> getClassesAsTeacher() {
        return classesAsTeacher;
    }

    public void setClassesAsTeacher(List<Class> classesAsTeacher) {
        this.classesAsTeacher = classesAsTeacher;
    }
}
