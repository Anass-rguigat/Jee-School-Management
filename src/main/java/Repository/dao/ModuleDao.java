package Repository.dao;

import Modules.Module;
import java.util.List;

public interface ModuleDao {
    void addModule(Module module);
    void updateModule(Module module);
    void deleteModule(int id);
    Module getModuleById(int id);
    List<Module> getAllModules();
}
