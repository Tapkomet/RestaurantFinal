package ua.training.controller.commands;

import ua.training.model.entity.Check;
import ua.training.model.service.CheckService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderListCommand implements Command {
    private CheckService checkService;

    public OrderListCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Check> orders = checkService.getAllOrders();
        request.setAttribute("checks" , orders);
        return "/WEB-INF/checklist.jsp";
    }
}