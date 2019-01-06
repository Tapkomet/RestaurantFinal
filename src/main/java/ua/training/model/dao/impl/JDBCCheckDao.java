package ua.training.model.dao.impl;

import ua.training.model.dao.CheckDao;
import ua.training.model.dao.mapper.CheckMapper;
import ua.training.model.dao.mapper.ItemMapper;
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
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        int id = check.getId();
        long totalPrice = check.getTotalPrice();
        int clientId = check.getClient().getId();
        Timestamp createTime = check.getCreateTime();
        connection.setAutoCommit(false);
        PreparedStatement stmt = connection.prepareStatement(
                "insert into cheque (check_id, total_price, client_id, create_time)" +
                        " values (?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setLong(2, totalPrice);
        stmt.setInt(3, clientId);
        stmt.setTimestamp(4, createTime);
        stmt.addBatch();
        stmt.executeBatch();
        stmt = connection.prepareStatement(
                "insert into item_in_check (item_id, name, price, number, check_id, category)" +
                        " values (?, ?, ?, ?, ?, ?)");
        for (Item item : check.getItems()) {
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setLong(3, item.getPrice());
            stmt.setInt(4, item.getNumber());
            stmt.setInt(5, item.getCheck().getId());
            stmt.setString(6, item.getCategory());
            stmt.addBatch();
        }
        stmt.executeBatch();
        
        stmt = connection.prepareStatement
                ("update item set number = number - ?" +
                        " where item_id = ?");
        for(Item item : check.getItems()){
            stmt.setInt(2, item.getId());
            stmt.setInt(1, item.getNumber());
            stmt.addBatch();
        }
        stmt.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        stmt.close();
        connection.close();
    }

    @Override
    public Check findById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "select * from cheque" +
                        " left join item_in_check on cheque.check_id = item_in_check.check_id" +
                        " where cheque.check_id = (?) ");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        CheckMapper checkMapper = new CheckMapper();

        rs.next();
        Check check = checkMapper.extractFromResultSet(rs);

        List<Item> items = new ArrayList<>();
        ItemMapper itemMapper = new ItemMapper();
        do {
            Item item = itemMapper
                    .extractFromResultSetForCheck(rs);
            items.add(item);
        }
        while(rs.next());
        check.setItems(items);

        stmt.close();
        connection.close();
        return check;
    }



    @Override
    public Check findOrderById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "select * from cheque" +
                        " left join item_in_check on cheque.check_id = item_in_check.check_id" +
                        " where cheque.check_id = (?) ");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        CheckMapper checkMapper = new CheckMapper();

        rs.next();
        Check check = checkMapper.extractFromResultSet(rs);

        List<Item> items = new ArrayList<>();
        ItemMapper itemMapper = new ItemMapper();
        do {
            Item item = itemMapper
                    .extractFromResultSetForCheck(rs);
            items.add(item);
        }
        while(rs.next());
        check.setItems(items);

        stmt.close();
        connection.close();
        return check;
    }

    @Override
    public List<Check> findAll() throws SQLException {
        Map<Integer, Check> checks = new HashMap<>();

        final String query = "" +
                "select * from cheque";
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
    public List<Check> findAllOrdersByUser(int id) throws SQLException {
        Map<Integer, Check> checks = new HashMap<>();

        PreparedStatement stmt = connection.prepareStatement(
                "select * from cheque WHERE client_id = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

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
    public void issue(Check check) throws SQLException {
        int id = check.getId();
        PreparedStatement stmt = connection.prepareStatement(
                "update cheque set is_check = true" +
                        " where check_id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public void confirm(Check check) throws SQLException {
        int id = check.getId();
        int adminId = check.getAdmin().getId();
        PreparedStatement stmt = connection.prepareStatement(
                "update cheque set is_confirmed = true, admin_id = ?" +
                        " where check_id = ?");
        stmt.setInt(1, adminId);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public void pay(Check check) throws SQLException {
        int id = check.getId();
        long tip = check.getTip();
        PreparedStatement stmt = connection.prepareStatement(
                "update cheque set is_paid = true, tip = ?" +
                        " where check_id = ?");
        stmt.setLong(1, tip);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }


    @Override
    public void update(Check check) {

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "delete from cheque where check_id = (?)");
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

    @Override
    public int getCount() throws SQLException {
        return 0;
    }
}