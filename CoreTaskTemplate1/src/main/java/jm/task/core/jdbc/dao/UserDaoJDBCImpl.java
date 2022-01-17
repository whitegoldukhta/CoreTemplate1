package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;
    Statement stmt;

    public UserDaoJDBCImpl() {
        connection = Util.connection();
    }

    public void createUsersTable() {
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            // создание таблицы
            String sql = "CREATE TABLE IF NOT EXISTS USER " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                if (stmt != null) {
                    stmt.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            String sql = "DROP TABLE USER";
            stmt.executeUpdate(sql);
            System.out.println("Таблица успешно удалена");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                if (stmt != null) {
                    stmt.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO USER (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                if (stmt != null) {
                    stmt.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM USER WHERE id = id";
            stmt.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                if (stmt != null) {
                    stmt.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM USER");
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(id, name, lastName, age);
                list.add(user);
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                if (stmt != null) {
                    stmt.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            String sql = "TRUNCATE TABLE USER";
            stmt.executeUpdate(sql);
            System.out.println("Таблица очищена");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                if (stmt != null) {
                    stmt.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
