package ua.training.controller.commands.check;

import ua.training.controller.commands.check.OrderListCommand;
import ua.training.controller.util.Path;
import ua.training.controller.util.Regex;
import ua.training.model.entity.Check;
import ua.training.model.entity.Item;
import ua.training.model.entity.User;
import ua.training.model.service.CheckService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddOrderCommand implements ua.training.controller.commands.Command {
    private CheckService checkService;

    public AddOrderCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Check check = new Check();
        long totalPrice = 0;
        int i = 1;

        List<Item> items = new ArrayList<Item>();
        int id;
        String name;
        long price;
        int number;
        String category;


        while (request.getParameter("id" + i) != null) {
            id = Integer.parseInt(request.getParameter("id" + i));
            name = request.getParameter("name" + i);
            price = (long) Double.parseDouble(request.getParameter("priceToAdd" + i));
            number = Integer.parseInt(request.getParameter("numberToAdd" + i));
            category = request.getParameter("category" + i);
            totalPrice += price;

            Item item = new Item.Builder(id)
                    .itemName(name)
                    .price(price)
                    .number(number)
                    .category(category)
                    .build();
            item.setCheck(check);
            items.add(item);
            i++;
        }

        items = mergeItems(items);
        check.setTotalPrice(totalPrice);
        check.setClient((User) ((HttpServletRequest) request).getSession().getAttribute("user"));
        check.setItems(items);
        Timestamp time = new Timestamp(new Date().getTime());
        check.setCreateTime(time);
        check.setId(check.hashCode());

        checkService.create(check);
        redirect(request, response, Path.CLIENT_ORDERS);
    }

    private List<Item> mergeItems(List<Item> items) {
        List<Item> mergedItems = new ArrayList<Item>();
        items.forEach(item -> {
            System.out.println(item);
            boolean presentInMerged = false;
            for (Item mergedItem : mergedItems) {
                if (item.getId() == mergedItem.getId()) {
                    System.out.println("merger");
                    presentInMerged = true;
                    mergedItem.setNumber(mergedItem.getNumber() + item.getNumber());
                }
            }
            if (!presentInMerged) {
                mergedItems.add(item);
            }
        });
        return mergedItems;
    }
}