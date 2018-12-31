package ua.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;



public class LoginUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private UserService userService ;

    public LoginUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if( email == null || email.equals("") || pass == null || pass.equals("")  ){
            forward(request, response, "/login.jsp");
        }
        if(CommandUtility.checkUserIsLogged(request, email)){
            forward(request, response, "/WEB-INF/error.jsp");
        }
        Optional<User> user = userService.login(email);
        if( user.isPresent() && user.get().getPassword().equals(pass)){
            request.getSession().setAttribute("user" , user.get());
            CommandUtility.setUser(request, user.get());
            logger.info("User "+ email+" logged successfully.");
            if(user.get().getRole()==User.ROLE.ADMIN){
                forward(request, response, "/api/admin");
            }
            if(user.get().getRole()==User.ROLE.CLIENT){
                forward(request, response, "/api/client");
            }
            forward(request, response, "/WEB-INF/error.jsp");

        }
        logger.info("Invalid attempt of login user:'"+ email+"'");
        forward(request, response, "/login.jsp");
    }
}
