package dtos;

import java.io.Serializable;

public class InstitutionDTO implements Serializable{

    private int code;
    private String name;
    
    public InstitutionDTO(){
    }
    
    public InstitutionDTO(int code, String name){
        this.code = code;
        this.name = name;
    }
    
    public void reset(){
        code = 0;
        name = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
