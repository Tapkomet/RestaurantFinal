package ua.training.controller.commands.item;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Item;
import ua.training.model.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ItemListCommand implements Command {
    private ItemService itemService;

    public ItemListCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> items = itemService.getAllItems();
        request.setAttribute("items" , items);
        forward(request, response, "/WEB-INF/itemlist.jsp");
    }
}
