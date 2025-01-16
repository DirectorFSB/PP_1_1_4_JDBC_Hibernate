package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users ( id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "\t\t\t\t\t\t\t\t\t\tname VARCHAR(255) ,\n" +
                    "                                        lastname VARCHAR(255)  ,\n" +
                    "                                        age INT );").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }

        }
        finally {
            session.close();
        }


    }


    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }

        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.createNativeQuery("INSERT INTO users ( name , lastname , age) VALUES (" + "'" + name + "'" + "," + "'" + lastName + "'" + "," + "'" + age + "'" + ")").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }

        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.createNativeQuery("DELETE from users where id =" + id).executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }

        }
        finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            users = session.createNativeQuery("select * from users" , User.class).getResultList();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }

        }
        finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.createNativeQuery("DELETE from users").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }

        }
        finally {
            session.close();
        }
    }
}
