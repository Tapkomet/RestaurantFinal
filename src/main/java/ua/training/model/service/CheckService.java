package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.CheckDao;
import ua.training.model.entity.Check;

import java.sql.SQLException;
import java.util.List;

public class CheckService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Check> getAllChecks() {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            try {
                return checkDao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public List<Check> getAllOrders() {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            try {
                return checkDao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void create(Check check) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.create(check);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Check getCheckById(int id) {
        try (CheckDao dao = daoFactory.createCheckDao()) {
            return dao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}