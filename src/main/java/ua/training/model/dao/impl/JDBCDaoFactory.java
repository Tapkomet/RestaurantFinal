
package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ItemDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.CheckDao;

import ua.training.model.dao.impl.JDBCCheckDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ItemDao createItemDao() {
        return new JDBCItemDao(getConnection());
    }

    @Override
    public CheckDao createCheckDao() {
        return new JDBCCheckDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}