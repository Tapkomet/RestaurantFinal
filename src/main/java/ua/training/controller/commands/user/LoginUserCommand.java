package ua.training.controller.commands.user;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Regex;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.model.service.exception.LoginException;
import ua.training.model.service.exception.WrongEmailException;
import ua.training.model.service.exception.WrongPasswordException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class LoginUserCommand implements Command {

    private UserService userService;

    public LoginUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if (email == null || email.equals("")) {
            request.setAttribute("email_error_message", "Put in the email");
            forward(request, response, "/login.jsp");
            return;
        }
        if(pass == null || pass.equals("")){
            request.setAttribute("password_error_message", "Put in the password");
            forward(request, response, "/login.jsp");
            return;
        }
        if (!Regex.isPasswordCorrect(pass)) {
            request.setAttribute("password_error_message", "Invalid password");
            forward(request, response, "/login.jsp");
            return;
        }
        if (!Regex.isEmailCorrect(email)) {
            request.setAttribute("email_error_message", "Invalid email");
            forward(request, response, "/login.jsp");
            return;
        }

        if (CommandUtility.checkUserIsLogged(request, email)) {
            forward(request, response, "/api/exception");
            return;
        }
        try {
            Optional<User> user = userService.login(email, pass);
            CommandUtility.setUser(request, user.get());
            if (user.get().getRole() == User.ROLE.ADMIN) {
                redirect(request, response, "/api/admin");
                return;

            }
            if (user.get().getRole() == User.ROLE.CLIENT) {
                redirect(request, response, "/api/client");
                return;
            }
            forward(request, response, "/api/exception");
            return;
        } catch (WrongEmailException e) {
            request.setAttribute("email_error_message", "Wrong email");
        } catch (WrongPasswordException e) {
            request.setAttribute("password_error_message", "Wrong password");
        } catch (LoginException e) {
            request.setAttribute("login_error_message", "Login failed");
        }
        forward(request, response, "/login.jsp");
    }
}