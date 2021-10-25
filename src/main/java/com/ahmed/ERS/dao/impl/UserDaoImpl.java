package com.ahmed.ERS.dao.impl;

import com.ahmed.ERS.dao.DAOUtilities;
import com.ahmed.ERS.dao.UserDao;
import com.ahmed.ERS.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            stmt = connection.createStatement();

            String sql = "SELECT * FROM USER";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User a = new User();
                a.setId(rs.getLong("user_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setFirstName(rs.getString("firstName"));
                a.setLastName(rs.getString("lastName"));
                a.setEmail(rs.getString("email"));
                a.setUserRole(rs.getString("userRole"));

                users.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    @Override
    public User getUser(int userid) {
        User user = new User();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            String sql = "SELECT * FROM USER WHERE user_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userid);


            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setUserRole(rs.getString("userRole"));
            }else {
                user.setId(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User getUserByName(String username) {
        User user = new User();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            String sql = "SELECT * FROM USER WHERE username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);


            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setUserRole(rs.getString("userRole"));
            }else {
                user.setId(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    @Override
    public void deleteUser(int userid) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            String sql = "DELETE FROM USER WHERE user_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userid);

            stmt.executeUpdate();
            System.out.println("User Deleted!!");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User saveUser(User userToRegister) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO USER VALUES (?,?,?,?,?,?,?)";

            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setLong(1, userToRegister.getId());
            stmt.setString(2, userToRegister.getUsername());
            stmt.setString(3, userToRegister.getPassword());
            stmt.setString(4, userToRegister.getFirstName());
            stmt.setString(5, userToRegister.getLastName());
            stmt.setString(6, userToRegister.getEmail());
            stmt.setString(7, userToRegister.getUserRole());


            success = stmt.executeUpdate();
            return userToRegister;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Insert user failed: " + userToRegister);
        }

        return userToRegister;
    }


    @Override
    public void updateUserRole(User userRoleToUpdate) throws Exception {

        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "update user set userRole = ? where user_id = ?";


            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setString(1, userRoleToUpdate.getUserRole());
            stmt.setLong(2, userRoleToUpdate.getId());


            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Failed to Update User Role > : " + userRoleToUpdate);
        }
    }

    @Override
    public void updateUser(User userToUpdate) throws Exception {

        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "update user set username = ?,password = ?,firstName = ?, lastName = ?,email = ?, userRole = ? where user_id = ?";


            // Setup PreparedStatement
            stmt = connection.prepareStatement(sql);

            // Add parameters from user into PreparedStatement
            stmt.setString(1, userToUpdate.getUsername());
            stmt.setString(2, userToUpdate.getPassword());
            stmt.setString(3, userToUpdate.getFirstName());
            stmt.setString(4, userToUpdate.getLastName());
            stmt.setString(5, userToUpdate.getEmail());
            stmt.setString(6, userToUpdate.getUserRole());
            stmt.setLong(7, userToUpdate.getId());

            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            // then update didn't occur, throw an exception
            throw new Exception("Failed to Update User > : " + userToUpdate);
        }
    }



}
