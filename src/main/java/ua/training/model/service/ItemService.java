package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ItemDao;
import ua.training.model.entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Item> getAllItems(){
        try (ItemDao itemDao = daoFactory.createItemDao()) {
            return itemDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addItem(int id, String name, int number, long price) {
        try (ItemDao itemDao = daoFactory.createItemDao()) {
            itemDao.addItem(id, name, number, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void create(Item item){
        try (ItemDao itemDao = daoFactory.createItemDao()) {
            itemDao.create(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
