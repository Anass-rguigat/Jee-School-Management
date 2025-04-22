package Controllers;

import Modules.Note;
import Modules.Student;
import Repository.dao.ModuleDao;
import Repository.dao.NoteDao;
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

@WebServlet("/students")
public class StudentController extends HttpServlet {
    private StudentDaoImpl studentDao;

    @Override
    public void init() {
        studentDao = new StudentDaoImpl(ConnectionManager.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/students/student-add.jsp").forward(req, resp);
                break;

            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                Student student = studentDao.getStudentById(editId);
                req.setAttribute("student", student);
                req.getRequestDispatcher("/WEB-INF/views/students/student-edit.jsp").forward(req, resp);
                break;

            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                studentDao.deleteStudent(deleteId);
                resp.sendRedirect("students");
                break;

            case "search":
                int searchId = Integer.parseInt(req.getParameter("id"));
                Student searchedStudent = studentDao.getStudentById(searchId);
                if (searchedStudent != null) {
                	ModuleDao moduleDao = new ModuleDaoImpl(ConnectionManager.getConnection());
                    NoteDao noteDao = new NoteDaoImpl(ConnectionManager.getConnection(), studentDao, moduleDao);
                    StudentService studentService = new StudentService();
                    
                    List<Note> notes = noteDao.getNotesByStudentId(searchId);
                    double average = studentService.calculerMoyenne(searchedStudent, notes);
                    
                    req.setAttribute("searchedStudent", searchedStudent);
                    req.setAttribute("searchedAverage", String.format("%.2f", average));
                } else {
                    req.setAttribute("notFound", "Aucun étudiant trouvé avec l'ID " + searchId);
                }
                List<Student> allStudents = studentDao.getAllStudents(); // 🌟 garder la liste complète
                req.setAttribute("students", allStudents);
                req.getRequestDispatcher("/WEB-INF/views/students/students.jsp").forward(req, resp);
                break;

            default:
                List<Student> students = studentDao.getAllStudents();
                req.setAttribute("students", students);
                req.getRequestDispatcher("/WEB-INF/views/students/students.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        Student student = new Student(id, firstName, lastName);

        if (id == 0) {
            studentDao.addStudent(student); // Ajouter un nouvel étudiant
        } else {
            studentDao.updateStudent(student); // Mettre à jour un étudiant existant
        }
        resp.sendRedirect("students");
    }
}
