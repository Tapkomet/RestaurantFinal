package ua.training.controller.commands;

import ua.training.controller.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionCommand implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("code", statusCode);
        switch(statusCode){
            case 500: request.setAttribute("message", "Internal server error");
                break;

            case 404: request.setAttribute("message", "Not found");
                break;

            default: request.setAttribute("message", "An error has occurred");
        }

        forward(request, response, Path.ERROR);
    }
}