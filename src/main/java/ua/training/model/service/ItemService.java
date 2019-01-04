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
            try {
                return itemDao.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public Item getItemById(int id){
        try (ItemDao dao = daoFactory.createItemDao()) {
            return dao.findById(id);
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

    public void update(Item item){
        try (ItemDao itemDao = daoFactory.createItemDao()) {
            itemDao.update(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try (ItemDao itemDao = daoFactory.createItemDao()) {
            itemDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemsSortedBy(String sortBy) {
        try (ItemDao itemDao = daoFactory.createItemDao()) {
            List<Item> items = itemDao.findAll();
            switch (sortBy) {
                case "id":
                    items.sort(Item.ItemIdComparator);
                    break;
                case "name":
                    items.sort(Item.ItemNameComparator);
                    break;
                case "price":
                    items.sort(Item.ItemPriceComparator);
                    break;
                default:
                    break;
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}