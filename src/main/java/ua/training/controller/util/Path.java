package ua.training.controller.util;
public interface Path {
    String ADMIN = "/api/admin";
    String ADMIN_USERS = "/api/admin/users";
    String ADMIN_EDIT_USER = "/api/admin/users/edit";
    String CLIENT = "/api/client";
    String CLIENT_ORDERS = "/api/client/orders";
    String CLIENT_ORDERS_ADD = "/api/client/orders/add";
    String ADMIN_ITEMS = "/api/admin/items";
    String ADMIN_ITEM = "/api/admin/item";
    String ADMIN_ADD_ITEM = "/api/admin/addItem";
    String ADMIN_EDIT_ITEM = "/api/admin/editItem";
    String ADMIN_DELETE_ITEM = "/api/admin/deleteItem";
    String USER_LOGIN = "/api/user-login";
    String USER_LOGOUT = "/api/logout";
    String USER_REGISTER = "/api/user-register";
    String EXCEPTION = "/api/exception";

    String ADMIN_BASE = "/WEB-INF/adminbase.jsp";
    String CLIENT_BASE = "/WEB-INF/clientbase.jsp";
    String CHECK_LIST = "/WEB-INF/checklist.jsp";
    String ORDER_LIST = "/WEB-INF/orderlist.jsp";
    String ERROR = "/WEB-INF/error.jsp";
    String ITEM_LIST = "/WEB-INF/itemlist.jsp";
    String CLIENT_ITEM_LIST = "/WEB-INF/clientitemlist.jsp";
    String USER_LIST = "/WEB-INF/userlist.jsp";
    String ITEM = "/WEB-INF/item.jsp";
    String CHECK = "/WEB-INF/check.jsp";
    String ORDER = "/WEB-INF/order.jsp";
    String INDEX = "/index.jsp";
    String LOGIN = "/login.jsp";
    String REGISTRATION = "/registration.jsp";
}
