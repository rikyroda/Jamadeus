/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author luisvf7
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllInstitutions",
    query = "SELECT i FROM Institution i")})
public class Institution extends User implements Serializable {
    
    @OneToMany(mappedBy="institution", cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
    private List<Teacher> orientingTeachers;

    @OneToOne(mappedBy="institution")
    private Teacher superviser;
    
    public Institution() {
        this.orientingTeachers = new LinkedList<>();
    }

    public Institution(String username, 
            String password,
            String name,
            String email,
            LinkedList<Teacher> orientingTeachers,
            Teacher superviser) {
        super(username,password,name,email);
        this.orientingTeachers = new LinkedList<>(orientingTeachers);
        this.superviser = superviser;
    }
    
    @Override
    public String toString() {
        return "Institution[Name= "+getName()+"]";
    }
    
    public void addOrientingTeacher(Teacher t){
        if(t==null)
            return;
        
        getOrientingTeachers().add(t);
    }
    
    public void removeOrientingTeacher(Teacher t){
        //Não sei se contains retorna sempre false visto as instancias serem sempre diferentes
        if(!orientingTeachers.contains(t))
            return;
        
        getOrientingTeachers().remove(t);
    }
    
    /**
     * @return the orientingTeachers
     */
    public List<Teacher> getOrientingTeachers() {
        return orientingTeachers;
    }

    public Teacher getSuperviser() {
        return superviser;
    }

    public void setSuperviser(Teacher superviser) {
        this.superviser = superviser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param orientingTeachers the orientingTeachers to set
     */
    public void setOrientingTeachers(List<Teacher> orientingTeachers) {
        this.orientingTeachers = orientingTeachers;
    }
  
}
