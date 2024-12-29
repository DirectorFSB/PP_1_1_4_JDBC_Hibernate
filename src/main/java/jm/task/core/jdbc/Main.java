package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
//        usHib.createUsersTable();
//        usHib.saveUser("Vova","Putin",(byte)72);
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Vova", "Putin", (byte) 72);

        User user = userService.getAllUsers().get(0);
        System.out.println(user);





//        String name = "Vladimir";
//        String lastName = "Putin";
//        Logger log = Logger.getLogger(Main.class.getName());
//        UserServiceImpl usi = new UserServiceImpl();
//        usi.createUsersTable();
//        usi.saveUser(name, lastName, (byte) 52);
//        usi.saveUser(name, lastName, (byte) 62);
//        usi.saveUser(name, lastName, (byte) 72);
//        usi.saveUser(name, lastName, (byte) 82);
//        log.log(Level.INFO, usi.getAllUsers().toString());
//        usi.cleanUsersTable();
//        usi.dropUsersTable();

    }
}
