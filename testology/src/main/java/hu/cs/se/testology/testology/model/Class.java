package hu.cs.se.testology.testology.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Class {

    @Column(name = "class_id")
    private Long id;

    private String className;
    private String description;
    private String accessCode;

    private List<Test> tests;
//    private List<User> students;
    private List<ClassRegistration> registrations;
    private User teacher;

    public Class(){
        registrations = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Column( unique = true)
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


//    @ManyToMany
//    @JoinTable(name = "student_class" , joinColumns = @JoinColumn(name = "class_id") , inverseJoinColumns = @JoinColumn(name = "student_id"))
//    public List<User> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<User> students) {
//        this.students = students;
//    }

    @OneToMany(mappedBy = "aClass" , cascade = CascadeType.ALL)
    public List<ClassRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<ClassRegistration> registrations) {
        this.registrations = registrations;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Class other = (Class) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
