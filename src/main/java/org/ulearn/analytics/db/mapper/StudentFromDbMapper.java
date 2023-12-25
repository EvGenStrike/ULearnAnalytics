package org.ulearn.analytics.db.mapper;

import org.ulearn.analytics.models.Student;
import org.ulearn.analytics.db.model.StudentEntity;

public class StudentFromDbMapper {
    public static Student map(StudentEntity student){
        return new Student(student.getName(), student.getSurname(), student.getGroup(), student.getCity());
    }
}
