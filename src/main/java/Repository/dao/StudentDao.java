package Repository.dao;

import Modules.Student;
import java.util.List;

public interface StudentDao {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}
