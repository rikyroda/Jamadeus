/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "INSTITUTIONS",
uniqueConstraints =
@UniqueConstraint(columnNames = {"CODE"}))
@NamedQueries({
    @NamedQuery(name = "getAllInstitutions",
    query = "SELECT i FROM Course i ORDER BY i.name")})
public class Institution implements Serializable {
    
    @Id
    @Column(name="CODE")
    private int code;
    
    @Column(name="NAME")
    @NotNull (message= "Institution name must not be empty")
    private String name;
    
    @OneToMany(mappedBy="institution", cascade = CascadeType.REMOVE)
    private final List<Teacher> orientingTeachers;

    @OneToOne(mappedBy="institution")
    private Teacher superviser;
    
    public Institution() {
        this.orientingTeachers = new LinkedList<>();
    }

    public Institution(int code, String name) {
        this.code = code;
        this.name = name;
        this.orientingTeachers = new LinkedList<>();
    }
    
    public int getCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return "Institution[Name= "+name+" Code=" + code + " ]";
    }
    
    public void addOrientingTeacher(Teacher t){
        if(t==null)
            return;
        
        orientingTeachers.add(t);
    }
    
    public void removeOrientingTeacher(Teacher t){
        //Não sei se contains retorna sempre false visto as instancias serem sempre diferentes
        if(!orientingTeachers.contains(t))
            return;
        
        orientingTeachers.remove(t);
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

   
    

    
}
