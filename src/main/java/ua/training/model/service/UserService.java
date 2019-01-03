package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.service.exception.LoginException;
import ua.training.model.service.exception.WrongEmailException;
import ua.training.model.service.exception.WrongPasswordException;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> login(String email, String pass) throws LoginException {
        Optional<User> result;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByEmail(email);
        }
        if (result.isPresent()) {
            if (result.get().getPassword().equals(pass)) {
                return result;
            }
            throw new WrongPasswordException("Password does not match.");
        }
        throw new WrongEmailException("User with email " + email + " is not found.");
    }

    public void register(String surname, String email, String pass){
        UserDao userDao = daoFactory.createUserDao();
        try {
            userDao.register(surname, email, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
