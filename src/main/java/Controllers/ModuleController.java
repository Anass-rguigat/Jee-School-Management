package Controllers;

import Modules.Module;
import Repository.daoImpl.ModuleDaoImpl;
import utils.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/modules")
public class ModuleController extends HttpServlet {
    private ModuleDaoImpl moduleDao;

    @Override
    public void init() {
        moduleDao = new ModuleDaoImpl(ConnectionManager.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/modules/module-add.jsp").forward(req, resp);
                break;

            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                Module module = moduleDao.getModuleById(editId);
                req.setAttribute("module", module);
                req.getRequestDispatcher("/WEB-INF/views/modules/module-edit.jsp").forward(req, resp);
                break;

            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                moduleDao.deleteModule(deleteId);
                resp.sendRedirect("modules");
                break;

            case "search":
                int searchId = Integer.parseInt(req.getParameter("id"));
                Module searchedModule = moduleDao.getModuleById(searchId);
                if (searchedModule != null) {
                    req.setAttribute("searchedModule", searchedModule);
                } else {
                    req.setAttribute("notFound", "Aucun module trouvé avec l'ID " + searchId);
                }
                List<Module> allModules = moduleDao.getAllModules(); // 🌟 ne pas supprimer les modules
                req.setAttribute("modules", allModules);
                req.getRequestDispatcher("/WEB-INF/views/modules/module-list.jsp").forward(req, resp);
                break;

            default:
                List<Module> modules = moduleDao.getAllModules();
                req.setAttribute("modules", modules);
                req.getRequestDispatcher("/WEB-INF/views/modules/module-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int coefficient = Integer.parseInt(req.getParameter("coefficient"));

        Module module = new Module(id, name, coefficient);

        if (id == 0) {
            moduleDao.addModule(module);
        } else {
            moduleDao.updateModule(module);
        }
        resp.sendRedirect("modules");
    }
}
