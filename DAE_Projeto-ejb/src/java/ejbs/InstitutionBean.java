package ejbs;

import dtos.CourseDTO;
import dtos.InstitutionDTO;
import entities.Course;
import entities.Institution;
import entities.Student;
import entities.Teacher;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless
public class InstitutionBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email, String orientingTeachers, String superviser)
            throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Institution.class, username) != null) {
                throw new EntityAlreadyExistsException("An institution with that username already exists.");
            }
            
            em.persist(new Institution(username, password, name,email,parseListTeachers(orientingTeachers),em.find(Teacher.class,superviser)));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<InstitutionDTO> getAll() {
        try {
            List<Institution> institutions = (List<Institution>) em.createNamedQuery("getAllInstitutions").getResultList();
            return institutionToDTOs(institutions);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(int code) throws EntityDoesNotExistsException {
        try {
            Institution institution = em.find(Institution.class, code);
            if (institution == null) {
                throw new EntityDoesNotExistsException("There is no institution with that code");
            }
            em.remove(institution);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    List<InstitutionDTO> institutionToDTOs(List<Institution> institutions) {
        List<InstitutionDTO> dtos = new ArrayList<>();
        for (Institution i : institutions) {
            dtos.add(new InstitutionDTO(i.getUsername(),i.getPassword(),i.getName(),i.getEmail(),i.getOrientingTeachers().toString(),i.getSuperviser().getUsername()));            
        }
        return dtos;
    }

    public void update(String username, String password, String name , String email, String orientingTeachers,String superviser) throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            Institution institution = em.find(Institution.class, username);
            if (institution == null) {
                throw new EntityDoesNotExistsException("There is no institution with that username.");
            }
            institution.setName(name);
            institution.setEmail(email);            
            institution.setPassword(password);
            institution.setOrientingTeachers(parseListTeachers(orientingTeachers));
            institution.setSuperviser(em.find(Teacher.class,superviser));
            em.merge(institution);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public LinkedList<Teacher> parseListTeachers(String s){
        String[] parts = s.split(",");
        LinkedList<Teacher> list = new LinkedList<>();
        for (String str : parts) {
            list.add( em.find(Teacher.class, str));
        }
        return list;
    }
}
