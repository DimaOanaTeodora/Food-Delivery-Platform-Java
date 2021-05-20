package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.product.Sweet;

import java.sql.*;

public class SweetRepository {
    // CallableStatement
    public void insertSweet(Sweet user) {
        String preparedSql = "{call insertSweet(?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, user.getName());
            cstmt.setDouble(2, user.getPrice());
            cstmt.setInt(3, user.getCalories());

            cstmt.registerOutParameter(1, Types.VARCHAR ); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added user with name:" + cstmt.getString(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Sweet getSweetByName(String name) {
        String selectSql ="SELECT * FROM user WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);//pozitia parametrului din select

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSweet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateSweetCalories(String name, int calories) {
        String updateNameSql = "UPDATE sweets SET calories=? WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            // trebuie puse in functie de ordinea parametrilor
            preparedStatement.setInt(1, calories);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Sweet mapToSweet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Sweet(resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4));
        }
        return null;
    }
}
