package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "getAllTeachers", query = "SELECT t FROM Teacher t ORDER BY t.name")
public class Teacher extends User implements Serializable {
    
    @ManyToOne
    private Institution institution;
    
    private String office;    
    
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    protected Teacher() {
        subjects = new LinkedList<>();
    }

    public Teacher(String username, String password, String name, String email, String office, Institution institution) {
        super(username, password, name, email);
        this.office = office;
        this.institution = institution;
        subjects = new LinkedList<>();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }    
    
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }    
    
}
