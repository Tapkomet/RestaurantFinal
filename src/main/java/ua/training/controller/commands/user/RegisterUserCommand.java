package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Path;
import ua.training.controller.util.Regex;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterUserCommand implements Command {

    private UserService userService ;

    public RegisterUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if (surname == null || surname.equals("")) {
            request.setAttribute("surname_error_message", "Put in the surname");
            forward(request, response, Path.REGISTRATION);
            return;
        }
        if (email == null || email.equals("")) {
            request.setAttribute("email_error_message", "Put in the email");
            forward(request, response, Path.REGISTRATION);
            return;
        }
        if(pass == null || pass.equals("")){
            request.setAttribute("password_error_message", "Put in the password");
            forward(request, response, Path.REGISTRATION);
            return;
        }


        if (!Regex.isEmailCorrect(email)) {
            request.setAttribute("email_error_message", "Invalid email");
            forward(request, response, Path.REGISTRATION);
            return;
        }
        if (!Regex.isSurnameCorrect(surname)) {
            request.setAttribute("surname_error_message", "Invalid name");
            forward(request, response, Path.REGISTRATION);
            return;
        }
        if (!Regex.isPasswordCorrect(pass)) {
            request.setAttribute("password_error_message", "Invalid password");
            forward(request, response, Path.REGISTRATION);
            return;
        }

            userService.register(surname, email, pass);

        request.setAttribute("index_message", "Registration successful");
        forward(request, response, Path.INDEX);
    }
}