package ua.training.controller.commands.check;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
import ua.training.model.entity.Check;
import ua.training.model.service.CheckService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderCommand implements Command {
    private CheckService checkService;

    public OrderCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Check check = checkService.getCheckById(id);
        request.setAttribute("check", check);
        request.setAttribute("items", check.getItems());
        System.out.println(check.getItems());
        forward(request, response, Path.CHECK);
    }
}