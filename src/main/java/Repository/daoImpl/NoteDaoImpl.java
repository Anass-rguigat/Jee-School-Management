package Repository.daoImpl;

import Modules.Note;
import Modules.Module;
import Modules.Student;
import Repository.dao.ModuleDao;
import Repository.dao.NoteDao;
import Repository.dao.StudentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDaoImpl implements NoteDao {
    private Connection connection;
    private StudentDao studentDao;
    private ModuleDao moduleDao;

    public NoteDaoImpl(Connection connection, StudentDao studentDao, ModuleDao moduleDao) {
        this.connection = connection;
        this.studentDao = studentDao;
        this.moduleDao = moduleDao;
    }

    @Override
    public void addNote(Note note) {
        String query = "INSERT INTO note(student_id, module_id, grade, absences) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, note.getStudent().getId());
            ps.setInt(2, note.getModule().getId());
            ps.setDouble(3, note.getGrade());
            ps.setInt(4, note.getAbsencesModule());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using a logger
        }
    }

    @Override
    public void updateNote(Note note) {
        String query = "UPDATE note SET student_id=?, module_id=?, grade=?, absences=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, note.getStudent().getId());
            ps.setInt(2, note.getModule().getId());
            ps.setDouble(3, note.getGrade());
            ps.setInt(4, note.getAbsencesModule());
            ps.setInt(5, note.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using a logger
        }
    }

    @Override
    public void deleteNote(int id) {
        String query = "DELETE FROM note WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using a logger
        }
    }

    @Override
    public Note getNoteById(int id) {
        if (studentDao == null || moduleDao == null) {
            throw new IllegalStateException("Les DAO ne sont pas correctement initialisés");
        }

        String query = "SELECT * FROM note WHERE id = ?"; // CORRIGÉ
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    int moduleId = rs.getInt("module_id");
                    double grade = rs.getDouble("grade");
                    int absences = rs.getInt("absences");
                    Student student = studentDao.getStudentById(studentId);
                    Module module = moduleDao.getModuleById(moduleId);

                    return new Note(id, student, module, grade, absences);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Note> getAllNotes() {
        List<Note> list = new ArrayList<>();
        String query = "SELECT * FROM note";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                // Get student
                Student student = studentDao.getStudentById(rs.getInt("student_id"));
                // Get module
                Module module = moduleDao.getModuleById(rs.getInt("module_id"));

                // Add note to the list
                list.add(new Note(
                    rs.getInt("id"),
                    student,
                    module,
                    rs.getDouble("grade"),
                    rs.getInt("absences")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using a logger
        }
        return list;
    }
    
    @Override
    public List<Note> getNotesByStudentId(int studentId) {
    	List<Note> notes = new ArrayList<>();
        String query = "SELECT * FROM note WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Note note = new Note(
                        rs.getInt("id"),
                        studentDao.getStudentById(studentId),
                        moduleDao.getModuleById(rs.getInt("module_id")),
                        rs.getDouble("grade"),
                        rs.getInt("absences")
                    );
                    notes.add(note);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

}
