package ua.training.model.dao;

import ua.training.model.entity.Item;

import java.sql.SQLException;

public interface ItemDao extends GenericDao<Item> {
    void addItem(int itemId, String name, int number, long price) throws SQLException;
}
