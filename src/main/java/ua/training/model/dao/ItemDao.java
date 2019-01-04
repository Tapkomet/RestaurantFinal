package ua.training.model.dao;

import ua.training.model.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {
    void addItem(int id, String name, int number, long price) throws SQLException;

    List<Item> findAllSorted(String sortBy) throws SQLException;

    List<Item> findNumberSorted(String sortBy, int integer, int offse) throws SQLException;
}
