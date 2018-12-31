package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.mapper.CheckMapper;
import ua.training.model.entity.Check;
import ua.training.model.entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCCheckDao implements CheckDao {
    private Connection connection;


    public JDBCCheckDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Check check) throws SQLException {
        int id = check.getId();
        long totalPrice = check.getTotalPrice();
        int clientId = check.getClient().getId();
        Timestamp createTime = check.getCreateTime();
        connection.setAutoCommit(false);
        PreparedStatement stmt = connection.prepareStatement(
                "insert into check (check_id, total_price, client_id, create_time)" +
                        " values (?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setLong(2, totalPrice);
        stmt.setInt(3, clientId);
        stmt.setTimestamp(4, createTime);
        stmt.addBatch();
        stmt = connection.prepareStatement(
                "insert into item_in_check (item_id, name, price, number, check_id)" +
                        " values (?, ?, ?, ?, ?)");
        for (Item item : check.getItems()) {
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setLong(3, item.getPrice());
            stmt.setInt(4, item.getNumber());
            stmt.setInt(5, item.getCheck().getId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        stmt.close();
        connection.close();
    }

    @Override
    public Check findById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "select * from check where check_id = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        CheckMapper checkMapper = new CheckMapper();

        rs.next();
        Check check = checkMapper.extractFromResultSet(rs);

        stmt.close();
        connection.close();
        return check;
    }

    @Override
    public List<Check> findAll() throws SQLException {
        Map<Integer, Check> checks = new HashMap<>();

        final String query = "" +
                " select * from check";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        CheckMapper checkMapper = new CheckMapper();

        while (rs.next()) {
            Check check = checkMapper
                    .extractFromResultSet(rs);
            check = checkMapper
                    .makeUnique(checks, check);
        }
        return new ArrayList<>(checks.values());
    }


    @Override
    public void update(Check check) {

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "delete from check where check_id = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        stmt.close();
        connection.close();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}