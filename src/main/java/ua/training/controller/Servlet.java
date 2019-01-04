package ua.training.controller;

import ua.training.controller.commands.*;
import ua.training.controller.commands.check.*;
import ua.training.controller.commands.item.*;
import ua.training.controller.commands.user.*;
import ua.training.controller.util.Path;
import ua.training.model.service.ItemService;
import ua.training.model.service.UserService;
import ua.training.model.service.CheckService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {

    private ItemService itemService = new ItemService();
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("admin/items",
                new ItemListCommand(new ItemService()));
        commands.put("admin/addItem",
                new AddItemCommand(new ItemService()));
        commands.put("admin/editItem",
                new EditItemCommand(new ItemService()));
        commands.put("admin/deleteItem",
                new DeleteItemCommand(new ItemService()));
        commands.put("user-login",
                new LoginUserCommand(new UserService()));
        commands.put("logout",
                new LogoutUserCommand());
        commands.put("user-register",
                new RegisterUserCommand(new UserService()));
        commands.put("exception", new ExceptionCommand());
        commands.put("client/order",
                new OrderCommand(new CheckService()));
        commands.put("admin/check",
                new CheckCommand(new CheckService()));
        commands.put("admin/checks",
                new CheckListCommand(new CheckService()));
        commands.put("client/orders",
                new OrderListCommand(new CheckService()));
        commands.put("client/orders/addPage",
                new OrderAddPageCommand(new ItemService()));
        commands.put("client/orders/add",
                new AddOrderCommand(new CheckService()));
        commands.put("admin/users", new UserListCommand(new UserService()));
        commands.put("admin/users/edit", new EditUserCommand(new UserService()));
        commands.put("client", new ClientCommand());
        commands.put("admin", new AdminCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/api/", "");
        Command command = commands.containsKey(path) ? commands.get(path) : commands.get(Path.INDEX);
        command.execute(request, response);
        //request.getRequestDispatcher(page).forward(request,response);
        //  response.getWriter().print("Hello from servlet");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
