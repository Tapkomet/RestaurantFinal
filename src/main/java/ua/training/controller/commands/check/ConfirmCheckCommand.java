package ua.training.controller.commands.check;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
import ua.training.model.entity.Check;
import ua.training.model.entity.User;
import ua.training.model.service.CheckService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmCheckCommand implements Command {
    private CheckService checkService;

    public ConfirmCheckCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User admin = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        Check check = new Check();
        check.setId(id);
        check.setAdmin(admin);
        checkService.confirm(check);
        redirect(request, response, Path.ADMIN_CHECKS);
    }
}