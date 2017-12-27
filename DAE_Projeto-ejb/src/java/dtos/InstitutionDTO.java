package dtos;

import entities.Teacher;
import java.io.Serializable;
import java.util.List;

public class InstitutionDTO extends UserDTO implements Serializable{
    private String orientingTeachers;
    private String superviser;
    
    public InstitutionDTO(){
    }
    
    public InstitutionDTO(String username, 
            String password,
            String name,
            String email,
            String orientingTeachers,
            String superviser){
        super(username, password, name, email);   
        this.orientingTeachers = orientingTeachers;
        this.superviser = superviser;
        
    }
    
    public void reset(){   
        name = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the orientingTeachers
     */
    public String getOrientingTeachers() {
        return orientingTeachers;
    }

    /**
     * @param orientingTeachers the orientingTeachers to set
     */
    public void setOrientingTeachers(String orientingTeachers) {
        this.orientingTeachers = orientingTeachers;
    }

    /**
     * @return the superviser
     */
    public String getSuperviser() {
        return superviser;
    }

    /**
     * @param superviser the superviser to set
     */
    public void setSuperviser(String superviser) {
        this.superviser = superviser;
    }

}
