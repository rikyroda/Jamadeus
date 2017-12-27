package web;

import dtos.CourseDTO;
import dtos.InstitutionDTO;
import dtos.PropostaDTO;
import dtos.StudentDTO;
import dtos.SubjectDTO;
import dtos.TeacherDTO;
import ejbs.CourseBean;
import ejbs.InstitutionBean;
import ejbs.PropostaBean;
import ejbs.StudentBean;
import ejbs.SubjectBean;
import ejbs.TeacherBean;
import enums.TipoTrabalho;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class AdministratorManager {

    @EJB
    private StudentBean studentBean;
    @EJB
    private TeacherBean teacherBean;
    @EJB
    private CourseBean courseBean;
    @EJB
    private SubjectBean subjectBean;
    @EJB
    private PropostaBean propostaBean;
    
    @EJB
    private InstitutionBean institutionBean;
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    private StudentDTO newStudent;
    private StudentDTO currentStudent;
    private CourseDTO newCourse;
    private CourseDTO currentCourse;
    private SubjectDTO newSubject;
    private SubjectDTO currentSubject;
    private UIComponent component;
    
    //Proposta
    private PropostaDTO newProposta;
    private PropostaDTO currentProposta;
    
    // Teacher
    private TeacherDTO newTeacher;
    private TeacherDTO currentTeacher;
    
    //Institutions
    private InstitutionDTO newInstitution;
    private InstitutionDTO currentInstitution;

    public AdministratorManager() {
        newStudent = new StudentDTO();
        newCourse = new CourseDTO();
        newSubject = new SubjectDTO();
        newInstitution = new InstitutionDTO();
        newProposta = new PropostaDTO();
        
    }

    /////////////// STUDENTS /////////////////
    public String createStudent() {
        try {
            studentBean.create(
                    newStudent.getUsername(),
                    newStudent.getPassword(),
                    newStudent.getName(),
                    newStudent.getEmail(),
                    newStudent.getCourseCode());
            newStudent.reset();
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }

        return "index?faces-redirect=true";
    }

    public List<StudentDTO> getAllStudents() {
        try {
            return studentBean.getAll();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String updateStudent() {
        try {
            studentBean.update(
                    currentStudent.getUsername(),
                    currentStudent.getPassword(),
                    currentStudent.getName(),
                    currentStudent.getEmail(),
                    currentStudent.getCourseCode());

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
        return "index?faces-redirect=true";
    }

    public void removeStudent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("studentUsername");
            String id = param.getValue().toString();
            studentBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public List<SubjectDTO> getCurrentStudentSubjects() {
        try {
            return subjectBean.getStudentSubjects(currentStudent.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
    /////////////// PROPOSTAS /////////////////
    public String createProposta() {
        try {
            
            propostaBean.create(TipoTrabalho.DISSERTACAO,
                    newProposta.getTitulo(),
                    newProposta.getAreas_cientificas(),
                    newProposta.getProponentes(),
                    newProposta.getResumo(),
                    newProposta.getObjetivos(),
                    newProposta.getBibliografia(),
                    newProposta.getPlano_trabalhos(),
                    newProposta.getLocal_realizacao(),
                    newProposta.getRequisitos(),
                    newProposta.getOrcamento(),
                    newProposta.getApoios(),
                    null,
                    null
                    );
            newProposta.reset();
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }

        return "admin_proposta_list?faces-redirect=true";
    }

    public List<PropostaDTO> getAllPropostas() {
        try {
            return propostaBean.getAll();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String updateProposta() {
        try {
            propostaBean.update(currentProposta.getId(),
                    currentProposta.getTipoTrabalho(),
                    currentProposta.getTitulo(),
                    currentProposta.getAreas_cientificas(),
                    currentProposta.getProponentes(),
                    currentProposta.getResumo(),
                    currentProposta.getObjetivos(),
                    currentProposta.getBibliografia(),
                    currentProposta.getPlano_trabalhos(),
                    currentProposta.getLocal_realizacao(),
                    currentProposta.getRequisitos(),
                    currentProposta.getOrcamento(),
                    currentProposta.getApoios(),
                    currentProposta.getOrientadores(),
                    currentProposta.getSupervisor());

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
        return "index?faces-redirect=true";
    }

    public void removeProposta(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("propostaId");
            int id = Integer.parseInt(param.getValue().toString());
            propostaBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    

    /////////////// COURSES /////////////////
    public String createCourse() {
        try {
            courseBean.create(
                    newCourse.getCode(),
                    newCourse.getName());
            newCourse.reset();
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }
        return "index?faces-redirect=true";
    }

    public List<CourseDTO> getAllCourses() {
        try {
            return courseBean.getAll();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public void removeCourse(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("courseCode");
            int code = Integer.parseInt(param.getValue().toString());
            courseBean.remove(code);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public List<SubjectDTO> getCurrentCourseSubjects() {
        try {
            return subjectBean.getCourseSubjects(currentCourse.getCode());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    /////////////// SUBJECTS /////////////////
    public String createSubject() {
        try {
            subjectBean.create(
                    newSubject.getCode(),
                    newSubject.getName(),
                    newSubject.getCourseCode(),
                    newSubject.getCourseYear(),
                    newSubject.getScholarYear());
            newSubject.reset();
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
        return "index?faces-redirect=true";
    }

    public List<SubjectDTO> getAllSubjects() {
        try {
            return subjectBean.getAll();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public void removeSubject(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("subjectCode");
            int code = Integer.parseInt(param.getValue().toString());
            subjectBean.remove(code);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public List<StudentDTO> getEnrolledStudents() {
        try {
            return studentBean.getEnrolledStudents(currentSubject.getCode());
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public List<StudentDTO> getUnrolledStudents() {
        try {
            return studentBean.getUnrolledStudents(currentSubject.getCode());
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public void enrollStudent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("studentUsername");
            String username = param.getValue().toString();
            studentBean.enrollStudent(username, currentSubject.getCode());
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public void unrollStudent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("studentUsername");
            String username = param.getValue().toString();
            studentBean.unrollStudent(username, currentSubject.getCode());
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public String createTeacher() {
        try {
            studentBean.create(
                    newStudent.getUsername(),
                    newStudent.getPassword(),
                    newStudent.getName(),
                    newStudent.getEmail(),
                    newStudent.getCourseCode());
            newStudent.reset();
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }

        return "admin_teachers_list?faces-redirect=true";
    }

    public List<TeacherDTO> getAllTeachers() {
        try {
            return teacherBean.getAllTeachers();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public String updateTeacher() {
        try {
            teacherBean.update(
                    currentTeacher.getUsername(),
                    currentTeacher.getName(),
                    currentTeacher.getEmail(),
                    currentTeacher.getOffice());

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
        return "admin_teachers_list?faces-redirect=true";
    }
    
    public void removeTeacher(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("teacherUsername");
            String id = param.getValue().toString();
            teacherBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public void removeInstitution(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("teacherUsername");
            int id = Integer.parseInt(param.getValue().toString());
            institutionBean.remove(id);
        } catch(EntityDoesNotExistsException e){
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } 
        catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public String updateInstitution() {
        InstitutionDTO i = currentInstitution;
        try {
            institutionBean.update(i.getUsername(),i.getPassword(),i.getName(),i.getEmail(),i.getOrientingTeachers(),i.getSuperviser());
        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
        return "admin_institutions_list?faces-redirect=true";
    }
    
    public List<InstitutionDTO> getAllInstitutions(){
        try {
            return institutionBean.getAll();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
    public String createInstitution(){
       try {
           InstitutionDTO i = newInstitution;
            institutionBean.create(i.getUsername(),i.getPassword(),i.getName(),i.getEmail(),i.getOrientingTeachers(),i.getSuperviser());
            
        } catch (EntityAlreadyExistsException | MyConstraintViolationException ex) {
            FacesExceptionHandler.handleException(ex, ex.getMessage(), component, logger);
            return null;
        } catch (Exception e){
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
            return null;
        }
        return "admin_institutions_list?faces-redirect=true";

    }
    
    /////////////// GETTERS & SETTERS /////////////////    
    public StudentDTO getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(StudentDTO newStudent) {
        this.newStudent = newStudent;
    }

    public StudentDTO getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(StudentDTO currentStudent) {
        this.currentStudent = currentStudent;
    }

    public CourseDTO getNewCourse() {
        return newCourse;
    }

    public void setNewCourse(CourseDTO newCourse) {
        this.newCourse = newCourse;
    }

    public CourseDTO getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(CourseDTO currentCourse) {
        this.currentCourse = currentCourse;
    }

    public SubjectDTO getNewSubject() {
        return newSubject;
    }

    public void setNewSubject(SubjectDTO newSubject) {
        this.newSubject = newSubject;
    }

    public SubjectDTO getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(SubjectDTO currentSubject) {
        this.currentSubject = currentSubject;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public TeacherDTO getNewTeacher() {
        return newTeacher;
    }

    public void setNewTeacher(TeacherDTO newTeacher) {
        this.newTeacher = newTeacher;
    }

    public TeacherDTO getCurrentTeacher() {
        return currentTeacher;
    }

    public void setCurrentTeacher(TeacherDTO currentTeacher) {
        this.currentTeacher = currentTeacher;
    }

    public void setCurrentInstitution(InstitutionDTO currentInstitution) {
        this.currentInstitution = currentInstitution;
    }

    public void setNewInstitution(InstitutionDTO newInstitution) {
        this.newInstitution = newInstitution;
    }

    public InstitutionDTO getNewInstitution() {
        return newInstitution;
    }

    public InstitutionDTO getCurrentInstitution() {
        return currentInstitution;
    }

    public PropostaDTO getNewProposta() {
        return newProposta;
    }

    public void setNewProposta(PropostaDTO newProposta) {
        this.newProposta = newProposta;
    }

    public PropostaDTO getCurrentProposta() {
        return currentProposta;
    }

    public void setCurrentProposta(PropostaDTO currentProposta) {
        this.currentProposta = currentProposta;
    }
    
    ///////////// VALIDATORS ////////////////////////
    public void validateUsername(FacesContext context, UIComponent toValidate, Object value) {
        try {
            //Your validation code goes here
            String username = (String) value;
            //If the validation fails
            if (username.startsWith("xpto")) {
                FacesMessage message = new FacesMessage("Error: invalid username.");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(toValidate.getClientId(context), message);
                ((UIInput) toValidate).setValid(false);
            }
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unkown error.", logger);
        }
    }

    
}
