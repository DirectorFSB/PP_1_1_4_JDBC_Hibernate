package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users ( id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "\t\t\t\t\t\t\t\t\t\tname VARCHAR(255) ,\n" +
                    "                                        lastname VARCHAR(255)  ,\n" +
                    "                                        age INT );").executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createNativeQuery("INSERT INTO users ( name , lastname , age) VALUES (" + "'" + name + "'" + "," + "'" + lastName + "'" + "," + "'" + age + "'" + ")").executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createNativeQuery("DELETE from users where id =" + id).executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            users = session.createNativeQuery("select * from users" , User.class).getResultList();
            session.getTransaction().commit();

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.createNativeQuery("DELETE from users").executeUpdate();
            session.getTransaction().commit();

        }
    }
}
