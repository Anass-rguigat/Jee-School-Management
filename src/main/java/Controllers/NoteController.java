package Controllers;

import Modules.Note;
import Modules.Module;
import Modules.Student;
import Repository.daoImpl.ModuleDaoImpl;
import Repository.daoImpl.NoteDaoImpl;
import Repository.daoImpl.StudentDaoImpl;
import ServicesImp.StudentService;
import utils.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/notes")
public class NoteController extends HttpServlet {
	private NoteDaoImpl noteDao;
    private StudentDaoImpl studentDao;
    private ModuleDaoImpl moduleDao;
    private StudentService studentService;

    @Override
    public void init() {
        studentDao = new StudentDaoImpl(ConnectionManager.getConnection());
        moduleDao = new ModuleDaoImpl(ConnectionManager.getConnection());
        noteDao = new NoteDaoImpl(ConnectionManager.getConnection(), studentDao, moduleDao);
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.setAttribute("students", studentDao.getAllStudents());
                req.setAttribute("modules", moduleDao.getAllModules());
                req.getRequestDispatcher("/WEB-INF/views/notes/note-add.jsp").forward(req, resp);
                break;

            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                Note note = noteDao.getNoteById(editId);
                req.setAttribute("note", note);
                req.setAttribute("students", studentDao.getAllStudents());
                req.setAttribute("modules", moduleDao.getAllModules());
                req.getRequestDispatcher("/WEB-INF/views/notes/note-edit.jsp").forward(req, resp);
                break;

            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                noteDao.deleteNote(deleteId);
                resp.sendRedirect("notes");
                break;

            case "search":
                int studentId = Integer.parseInt(req.getParameter("id"));
                Student student = studentDao.getStudentById(studentId);
                List<Note> searchedNotes = noteDao.getNotesByStudentId(studentId);
                
                if (student != null && !searchedNotes.isEmpty()) {
                    double average = studentService.calculerMoyenne(student, searchedNotes);
                    req.setAttribute("searchedStudent", student);
                    req.setAttribute("searchedAverage", String.format("%.2f", average));
                    req.setAttribute("searchedNotes", searchedNotes);
                } else {
                    req.setAttribute("notFound", "No grades found for student ID " + studentId);
                }
                // Ajouter toutes les notes pour le deuxième tableau
                req.setAttribute("allNotes", noteDao.getAllNotes()); 
                req.getRequestDispatcher("/WEB-INF/views/notes/notes.jsp").forward(req, resp);
                break;

            default:
                req.setAttribute("allNotes", noteDao.getAllNotes());
                req.getRequestDispatcher("/WEB-INF/views/notes/notes.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
String action = req.getParameter("action");
        
        // Gestion des absences
        if ("incrementAbsence".equals(action)) {
            handleAbsenceIncrement(req);
            resp.sendRedirect("notes");
            return;
        }

        // Gestion des notes
        handleNoteUpdate(req);
        resp.sendRedirect("notes");
    }

    private void handleAbsenceIncrement(HttpServletRequest req) {
        int noteId = Integer.parseInt(req.getParameter("id"));
        Note note = noteDao.getNoteById(noteId);
        if (note != null) {
            note.incrementAbsencesModule();
            noteDao.updateNote(note);
        }
    }

    private void handleNoteUpdate(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        int moduleId = Integer.parseInt(req.getParameter("moduleId"));
        double grade = Double.parseDouble(req.getParameter("grade"));

        Student student = studentDao.getStudentById(studentId);
        Module module = moduleDao.getModuleById(moduleId);
        
        Note note = createOrUpdateNote(id, student, module, grade);
        
        if (id == 0) {
            noteDao.addNote(note);
        } else {
            noteDao.updateNote(note);
        }
    }

    private Note createOrUpdateNote(int id, Student student, Module module, double grade) {
        if (id == 0) {
            return new Note(0, student, module, grade, 0);
        }
        
        Note existingNote = noteDao.getNoteById(id);
        if (existingNote != null) {
            return new Note(
                id,
                student,
                module,
                grade,
                existingNote.getAbsencesModule()
            );
        }
        return new Note(id, student, module, grade, 0);
    }
}
