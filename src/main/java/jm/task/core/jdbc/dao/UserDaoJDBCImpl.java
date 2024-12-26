package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private final Logger log = Logger.getLogger("jm.task.core.jdbc");
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement stat = Util.getMySQLConnection().createStatement()){
            stat.execute("CREATE TABLE IF NOT EXISTS users ( id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "\t\t\t\t\t\t\t\t\t\tname VARCHAR(255) ,\n" +
                    "                                        lastname VARCHAR(255)  ,\n" +
                    "                                        age INT );");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Statement stat = Util.getMySQLConnection().createStatement()){
            stat.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try(Statement stat = Util.getMySQLConnection().createStatement()){
            stat.execute("INSERT INTO users ( name , lastname , age) VALUES ("+"'"+name+"'"+ "," + "'"+ lastName +"'"+","+"'"+ age+"'"+")") ;
            log.log(Level.INFO,"User с именем — " + name + " добавлен в базу данных\n" ,new Object[]{name});
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Statement stat = Util.getMySQLConnection().createStatement()){
            stat.execute("DELETE from users where id =" + id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Statement stat = Util.getMySQLConnection().createStatement();ResultSet rs = stat.executeQuery("select * from users")){
            while(rs.next()){
                User user = new User(rs.getString("name"),rs.getString("lastname") , rs.getByte("age"));
                user.setId(rs.getLong("id"));
                users.add(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement stat = Util.getMySQLConnection().createStatement()){
            stat.execute("DELETE from users");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
