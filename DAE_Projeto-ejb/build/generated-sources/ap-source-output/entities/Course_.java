package entities;

import entities.Student;
import entities.Subject;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-28T18:01:03")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, Integer> code;
    public static volatile ListAttribute<Course, Subject> subjects;
    public static volatile SingularAttribute<Course, String> name;
    public static volatile ListAttribute<Course, Student> students;

}