package Repository.daoImpl;

import Modules.Student;
import Repository.dao.StudentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStudent(Student student) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO student(firstName, lastName) VALUES (?, ?)");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE student SET firstName=?, lastName=? WHERE id=?");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int id) {
        Student student = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM student WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
