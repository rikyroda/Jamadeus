package entities;

import entities.Subject;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-28T18:01:03")
@StaticMetamodel(Teacher.class)
public class Teacher_ extends User_ {

    public static volatile ListAttribute<Teacher, Subject> subjects;
    public static volatile SingularAttribute<Teacher, String> office;

}