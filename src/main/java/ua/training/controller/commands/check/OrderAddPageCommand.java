package ua.training.controller.commands.check;

import ua.training.controller.util.Path;
import ua.training.model.entity.Item;
import ua.training.model.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderAddPageCommand implements ua.training.controller.commands.Command {
    private ItemService itemService;

    public OrderAddPageCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortBy = request.getParameter("tosort");
        if (sortBy == null) {
            getAllItems(request, response);
        } else {
            List<Item> items = itemService.getItemsSortedBy(sortBy);
            request.setAttribute("items", items);
            forward(request, response, Path.CLIENT_ITEM_LIST);
        }
    }

    void getAllItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Requesting all items");
        List<Item> items = itemService.getAllItems();
        request.setAttribute("items", items);
        forward(request, response, Path.CLIENT_ITEM_LIST);
    }
}