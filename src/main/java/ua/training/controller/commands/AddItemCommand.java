package ua.training.controller.commands;

import ua.training.model.entity.Item;
import ua.training.model.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddItemCommand implements Command {
    private ItemService itemService;

    public AddItemCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("item_id"));
        String name = request.getParameter("name");
        int number = Integer.parseInt(request.getParameter("number"));
        long price = Long.parseLong(request.getParameter("price"));
        itemService.addItem(id, name, number, price);
        List<Item> items = itemService.getAllItems();
        request.setAttribute("items" , items);
        return "/WEB-INF/itemlist.jsp";
    }
}
