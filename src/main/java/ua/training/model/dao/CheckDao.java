package ua.training.model.dao;

import ua.training.model.entity.Check;

import java.sql.SQLException;
import java.util.List;

public interface CheckDao extends GenericDao<Check> {
    Check findOrderById(int id) throws SQLException;

    List<Check> findAllOrdersByUser(int id) throws SQLException;

    void issue(Check check) throws SQLException;

    void confirm(Check check) throws SQLException;

    void pay(Check check) throws SQLException;
}