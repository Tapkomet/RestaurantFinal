package ua.training.controller.commands.item;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Item;
import ua.training.model.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddItemCommand implements Command {
    private ItemService itemService;

    public AddItemCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("item_id"));
        String name = request.getParameter("name");
        int number = Integer.parseInt(request.getParameter("number"));
        long price = Long.parseLong(request.getParameter("price"));
        Item item = new Item.Builder(name)
                .number(number)
                .price(price)
                .build();
        itemService.create(item);
        List<Item> items = itemService.getAllItems();
        request.setAttribute("items" , items);
        forward(request, response, "/WEB-INF/itemlist.jsp");
    }
}
