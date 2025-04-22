package Repository.daoImpl;

import Modules.Module;
import Repository.dao.ModuleDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleDaoImpl implements ModuleDao {
    private Connection connection;

    public ModuleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addModule(Module module) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO module(name, coefficient) VALUES (?, ?)");
            ps.setString(1, module.getName());
            ps.setInt(2, module.getCoefficient());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModule(Module module) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE module SET name=?, coefficient=? WHERE id=?");
            ps.setString(1, module.getName());
            ps.setInt(2, module.getCoefficient());
            ps.setInt(3, module.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteModule(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM module WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Module getModuleById(int id) {
        Module module = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM module WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                module = new Module(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("coefficient")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return module;
    }

    @Override
    public List<Module> getAllModules() {
        List<Module> list = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM module");
            while (rs.next()) {
                list.add(new Module(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("coefficient")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
