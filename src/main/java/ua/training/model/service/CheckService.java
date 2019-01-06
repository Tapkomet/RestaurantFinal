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

    public Check getOrderById(int id) {
        try (CheckDao dao = daoFactory.createCheckDao()) {
            return dao.findOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Check> getAllOrdersByUser(int id) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            try {
                return checkDao.findAllOrdersByUser(id);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void confirm(Check check) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.confirm(check);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issue(Check check) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.issue(check);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pay(Check check) {
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.pay(check);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}