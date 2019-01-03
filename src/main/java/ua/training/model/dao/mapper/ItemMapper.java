package ua.training.model.dao.mapper;

import ua.training.model.entity.Check;
import ua.training.model.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ItemMapper implements ObjectMapper<Item> {


    @Override
    public Item extractFromResultSet(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getLong("price"));
        item.setNumber(rs.getInt("number"));
        return item;
    }

    
    public Item extractFromResultSetForCheck(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getLong("price"));
        item.setNumber(rs.getInt("number_sold"));
        Check check = new Check();
        check.setId(rs.getInt("check_id"));
        item.setCheck(check);
        return item;
    }

    @Override
    public Item makeUnique(Map<Integer, Item> cache,
                              Item item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
