package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, Path.CLIENT_BASE);
    }
}