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
        String category = item.getCategory();
        PreparedStatement stmt = connection.prepareStatement(
                "insert into item (item_id, name, number, price, category)" +
                        " values (?, ?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setInt(3, number);
        stmt.setLong(4, price);
        stmt.setString(5, category);
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
    public void update(Item item) throws SQLException {
        int id = item.getId();
        int number = item.getNumber();
        long price = item.getPrice();
        String category = item.getCategory();
        PreparedStatement stmt = connection.prepareStatement(
                "update item set number = ?, price = ?, category = ?" +
                        " where item_id = ?");
        stmt.setInt(4, id);
        stmt.setInt(1, number);
        stmt.setLong(2, price);
        stmt.setString(3, category);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "delete from item where item_id = (?)");
        stmt.setInt(1, id);
        stmt.executeUpdate();

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
        PreparedStatement preparedStatement = connection.prepareStatement
                ("select count(*) as count from item");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("count");
        }
        return 0;
    }

    @Override
    public void addItem(int id, String name, int number, long price, String category) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "insert into item (item_id, name, number, price, category)" +
                        " values (?, ?, ?, ?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setInt(3, number);
        stmt.setLong(4, price);
        stmt.setString(5, category);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public List<Item> findAllSorted(String sortBy) throws SQLException {
        Map<Integer, Item> items = new HashMap<>();

        PreparedStatement stmt = connection.prepareStatement(" select * from item ORDER BY ?");
        stmt.setString(1, sortBy);
        ResultSet rs = stmt.executeQuery();
        System.out.println(sortBy);

        ItemMapper itemMapper = new ItemMapper();

        while (rs.next()) {
            Item item = itemMapper
                    .extractFromResultSet(rs);
            System.out.println(item);
            itemMapper
                    .makeUnique(items, item);
        }
        return new ArrayList<>(items.values());
    }

    @Override
    public List<Item> findNumberSorted(String sortBy, int integer, int offset) throws SQLException {
        PreparedStatement stmt = null;
        switch (sortBy) {
            case "id":
                stmt = connection.prepareStatement
                        (" select * from item order by id+0 limit ? offset ?");
                break;
            case "name":
                stmt = connection.prepareStatement
                        (" select * from item order by name limit ? offset ?");
                break;
            case "price":
                stmt = connection.prepareStatement
                        (" select * from item order by price+0 limit ? offset ?");
                break;
            case "catgory":
                stmt = connection.prepareStatement
                        (" select * from item order by category limit ? offset ?");
                break;
            default:
                break;
        }
        stmt.setInt(1, integer);
        stmt.setInt(2, offset);

        ResultSet rs = stmt.executeQuery();

        List<Item> items = new ArrayList<>();
        ItemMapper itemMapper = new ItemMapper();

        while (rs.next()) {
            Item item = itemMapper
                    .extractFromResultSet(rs);
            items.add(item);
        }
        return items;
    }
}