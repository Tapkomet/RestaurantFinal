package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class ClientCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/clientbase.jsp";
    }
}