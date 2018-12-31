package ua.training.model.dao.impl;

import ua.training.model.dao.ItemDao;
import ua.training.model.dao.mapper.ItemMapper;
import ua.training.model.entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCItemDao implements ItemDao {
    private Connection connection;


    public JDBCItemDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Item item) throws SQLException {
        int id = item.getId();
        String name = item.getName();
        int number = item.getNumber();
        long price = item.getPrice();
        PreparedStatement stmt = connection.prepareStatement(
                "insert into item (item_id, name, number, price)" +
                        " values (?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setInt(3, number);
        stmt.setLong(4, price);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public Item findById(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "select * from item where item_id = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        ItemMapper itemMapper = new ItemMapper();

        rs.next();
        Item item = itemMapper.extractFromResultSet(rs);

        stmt.close();
        connection.close();
        return item;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        Map<Integer, Item> items = new HashMap<>();

        final String query = "" +
                " select * from item";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        ItemMapper itemMapper = new ItemMapper();

        while (rs.next()) {
            Item item = itemMapper
                    .extractFromResultSet(rs);
            item = itemMapper
                    .makeUnique(items, item);
        }
        return new ArrayList<>(items.values());
    }



    @Override
    public void update(Item item) {

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "delete from item where item_id = (?)");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        stmt.close();
        connection.close();
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItem(int id, String name, int number, long price) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "insert into item (item_id, name, number, price)" +
                        " values (?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setInt(3, number);
        stmt.setLong(4, price);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }
}