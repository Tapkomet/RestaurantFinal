package ua.training.controller.commands.item;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int number = Integer.parseInt(request.getParameter("number"));
        long price = Long.parseLong(request.getParameter("price"));
        String category = request.getParameter("category");
        Item item = new Item.Builder(id)
                .itemName(name)
                .number(number)
                .price(price)
                .category(category)
                .build();
        itemService.create(item);
        response.sendRedirect(request.getContextPath() + Path.ADMIN_ITEMS);
    }
}
