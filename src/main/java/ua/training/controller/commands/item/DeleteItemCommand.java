package ua.training.controller.commands.item;

import ua.training.model.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemCommand implements ua.training.controller.commands.Command {
    private ItemService itemService;

    public DeleteItemCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("item_id");
        int id = Integer.parseInt(sid);
        itemService.delete(id);
        response.sendRedirect(request.getContextPath() + "/api/admin/items");
    }
}