package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.product.Sweet;

import java.sql.*;

public class SweetRepository {

    // CallableStatement
    public void insertSweet(Sweet sweet) {
        String preparedSql = "{call insertSweet(?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);

            cstmt.setString(1, sweet.getName());
            cstmt.setDouble(2, sweet.getPrice());
            cstmt.setInt(3, sweet.getCalories());

            //out param (result of the procedure call)
            cstmt.registerOutParameter(1, Types.VARCHAR );

            cstmt.execute();

            //out param (result of the procedure call)
            System.out.println("Added sweet with name:" + cstmt.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Sweet getSweetByName(String name) {
        String selectSql ="SELECT * FROM sweets WHERE name=?";

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

    // PreparedStatement - use when we have parameters
    public void deleteSweetByName(String name) {
        String deleteSql ="DELETE FROM sweets WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);

            // trebuie puse in functie de ordinea parametrilor
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
