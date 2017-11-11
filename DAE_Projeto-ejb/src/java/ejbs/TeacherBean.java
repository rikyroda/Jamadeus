package ejbs;

import dtos.TeacherDTO;
import entities.Subject;
import entities.Teacher;
import entities.User;
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
public class TeacherBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email, String office)
            throws EntityAlreadyExistsException {
        try {
            if (em.find(User.class, username) != null) {
                throw new EntityAlreadyExistsException("A user with that username already exists.");
            }
            em.persist(new Teacher(username, password, name, email, office));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(String username, String name, String email, String office)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Teacher teacher = em.find(Teacher.class, username);
            if (teacher == null) {
                throw new EntityDoesNotExistsException("There is no teacher with that username.");
            }
            teacher.setName(name);
            teacher.setEmail(email);
            teacher.setOffice(office);
            em.merge(teacher);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<TeacherDTO> getAllTeachers() {
        try {
            List<Teacher> teachers = (List<Teacher>) em.createNamedQuery("getAllTeachers").getResultList();
            return teachersToDTOs(teachers);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<TeacherDTO> getSujectTeachers(int subjectCode)
            throws EntityDoesNotExistsException {
        try {
            Subject subject = em.find(Subject.class, subjectCode);
            if (subject == null) {
                throw new EntityDoesNotExistsException("There is no subject with that code.");
            }
            return teachersToDTOs(subject.getTeachers());
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<TeacherDTO> getTeachersNotInSubject(int subjectCode)
            throws EntityDoesNotExistsException {
        try {
            Subject subject = em.find(Subject.class, subjectCode);
            if (subject == null) {
                throw new EntityDoesNotExistsException("There is no subject with that code.");
            }
            List<Teacher> teachers = (List<Teacher>) em.createNamedQuery("getAllTeachers").getResultList();
            List<Teacher> teacher = subject.getTeachers();
            teachers.removeAll(teacher);
            return teachersToDTOs(teachers);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void addSubjectTeacher(int subjectCode, String username)
            throws EntityDoesNotExistsException {
        try {
            Subject subject = em.find(Subject.class, subjectCode);
            if (subject == null) {
                throw new EntityDoesNotExistsException("There is no subject with that code.");
            }
            Teacher teacher = em.find(Teacher.class, username);
            if (teacher == null) {
                throw new EntityDoesNotExistsException("There is no teacher with that username.");
            }
            subject.addTeacher(teacher);
            teacher.addSubject(subject);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeSubjectTeacher(int subjectCode, String username)
            throws EntityDoesNotExistsException {
        try {
            Subject subject = em.find(Subject.class, subjectCode);
            if (subject == null) {
                throw new EntityDoesNotExistsException("There is no subject with that code.");
            }
            Teacher teacher = em.find(Teacher.class, username);
            if (teacher == null) {
                throw new EntityDoesNotExistsException("There is no teacher with that username.");
            }
            subject.removeTeacher(teacher);
            teacher.removeSubject(subject);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    TeacherDTO teacherToDTO(Teacher teacher) {
        return new TeacherDTO(
                teacher.getUsername(),
                null,
                teacher.getName(),
                teacher.getEmail(),
                teacher.getOffice());
    }

    List<TeacherDTO> teachersToDTOs(List<Teacher> teachers) {
        List<TeacherDTO> dtos = new ArrayList<>();
        for (Teacher t : teachers) {
            dtos.add(teacherToDTO(t));
        }
        return dtos;
    }
    
}
