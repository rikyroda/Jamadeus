package ejbs;

import dtos.SubjectDTO;
import entities.Course;
import entities.Student;
import entities.Subject;
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
public class SubjectBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int code, String name, int courseCode, int courseYear, String scholarYear)
        throws EntityAlreadyExistsException, EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            if (em.find(Subject.class, code) != null) {
                throw new EntityAlreadyExistsException("A student with that username already exists.");
            }
            Course course = em.find(Course.class, courseCode);
            if (course == null) {
                throw new EntityDoesNotExistsException("There is no course with that code.");
            }
        
            Subject subject = new Subject(code, name, course, courseYear, scholarYear);
            em.persist(subject);
            course.addSubject(subject);
        
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException e) {
            throw e;           
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<SubjectDTO> getAll() {
        try {
            List<Subject> subjects = (List<Subject>) em.createNamedQuery("getAllSubjects").getResultList();
            return subjectsToDTOs(subjects);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
       
    public void remove(int code) throws EntityDoesNotExistsException {
        try {
            Subject subject = em.find(Subject.class, code);
            if (subject == null) {
                throw new EntityDoesNotExistsException("There is no subject with that code.");
            }
          
            subject.getCourse().removeSubject(subject);
            
            for (Student student : subject.getStudents()) {
                student.removeSubject(subject);
            }
            
            em.remove(subject);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }    

    public List<SubjectDTO> getStudentSubjects(String studentUsername) throws EntityDoesNotExistsException {
        try {
            Student student = em.find(Student.class, studentUsername);
            if (student == null) {
                throw new EntityDoesNotExistsException("Student does not exists.");
            }

            return subjectsToDTOs(student.getSubjects());
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<SubjectDTO> getCourseSubjects(int courseCode) throws EntityDoesNotExistsException {
        try {
            Course course = em.find(Course.class, courseCode);
            if (course == null) {
                throw new EntityDoesNotExistsException("Course does not exists.");
            }
            
            return subjectsToDTOs(course.getSubjects());
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }    
    
    List<SubjectDTO> subjectsToDTOs(List<Subject> subjects) {
        List<SubjectDTO> dtos = new ArrayList<>();
        for(Subject subject : subjects) {
            dtos.add(new SubjectDTO(
                    subject.getCode(),
                    subject.getName(),
                    subject.getCourse().getCode(),
                    subject.getCourse().getName(),
                    subject.getCourseYear(),
                    subject.getScholarYear()));
        }
        return dtos;
    }

}
