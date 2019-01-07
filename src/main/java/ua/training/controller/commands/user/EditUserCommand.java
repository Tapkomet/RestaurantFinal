package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserCommand implements Command {
    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = request.getParameter("role");
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        User user = new User();
        user.setId(id);
        user.setRoleFromString(role);
        userService.update(user);
        redirect(request, response, Path.ADMIN_USERS);
    }

}