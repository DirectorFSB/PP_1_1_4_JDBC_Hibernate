package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao usHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        usHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        usHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return usHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        usHibernate.cleanUsersTable();
    }
}
