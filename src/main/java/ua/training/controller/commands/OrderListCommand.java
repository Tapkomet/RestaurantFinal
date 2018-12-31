package ua.training.controller.commands;

import ua.training.model.entity.Check;
import ua.training.model.service.CheckService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListCommand implements Command {
    private CheckService checkService;

    public OrderListCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Check> orders = checkService.getAllOrders();
        request.setAttribute("checks" , orders);
        forward(request, response, "/WEB-INF/checklist.jsp");
    }
}