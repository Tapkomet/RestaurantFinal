package ua.training.controller.commands.item;

import ua.training.controller.commands.Command;
import ua.training.model.entity.Item;
import ua.training.model.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemCommand implements Command {
    private ItemService itemService;

    public ItemCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("item_id");
        int id = Integer.parseInt(sid);
        Item item = itemService.getItemById(id);
        request.setAttribute("item" , item);
        forward(request, response, "/WEB-INF/item.jsp");
    }
}