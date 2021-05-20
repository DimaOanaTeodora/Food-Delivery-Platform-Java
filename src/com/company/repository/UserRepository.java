package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.user.User;

import java.sql.*;

public class UserRepository {

    // CallableStatement
    public void insertUser(User user) {
        String preparedSql = "{call insertUser(?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, user.getName());
            cstmt.setString(2, user.getEmail());
            cstmt.setString(3, user.getPhoneNumber());
            cstmt.setString(4, user.getPassword());


            cstmt.registerOutParameter(1, Types.VARCHAR ); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with name:" + cstmt.getString(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public User getUserByName(String name) {
        String selectSql ="SELECT * FROM user WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);//pozitia parametrului din select

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateUserEmail(String newEmail, String name) {
        String updateNameSql = "UPDATE user SET email=? WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            // trebuie puse in functie de ordinea parametrilor
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
        }
        return null;
    }

}