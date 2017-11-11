package entities;

import entities.Course;
import entities.Student;
import entities.Teacher;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-11T17:32:48")
@StaticMetamodel(Subject.class)
public class Subject_ { 

    public static volatile SingularAttribute<Subject, Integer> courseYear;
    public static volatile SingularAttribute<Subject, Integer> code;
    public static volatile ListAttribute<Subject, Teacher> teachers;
    public static volatile SingularAttribute<Subject, String> name;
    public static volatile SingularAttribute<Subject, Course> course;
    public static volatile SingularAttribute<Subject, String> scholarYear;
    public static volatile ListAttribute<Subject, Student> students;

}