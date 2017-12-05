package ejbs;

import dtos.CourseDTO;
import dtos.InstitutionDTO;
import entities.Course;
import entities.Institution;
import entities.Student;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
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

    public void create(int code, String name)
            throws EntityAlreadyExistsException, MyConstraintViolationException {
        try {
            if (em.find(Institution.class, code) != null) {
                throw new EntityAlreadyExistsException("An institution with that code already exists.");
            }

            em.persist(new Institution(code, name));

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
            dtos.add(new InstitutionDTO(i.getCode(), i.getName()));            
        }
        return dtos;
    }

    public void update(int code, String name) throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            Institution institution = em.find(Institution.class, code);
            if (institution == null) {
                throw new EntityDoesNotExistsException("There is no student with that username.");
            }           
            institution.setName(name);            
            em.merge(institution);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
