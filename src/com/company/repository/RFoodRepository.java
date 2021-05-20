package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.product.RFood;

import java.sql.*;

public class RFoodRepository {
    // CallableStatement
    public void insertRFood(RFood rfood) {
        String preparedSql = "{call insertRFood(?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, rfood.getName());
            cstmt.setDouble(2, rfood.getPrice());
            cstmt.setString(3, rfood.getIngredients());
            
            cstmt.registerOutParameter(1, Types.VARCHAR ); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added restaurant meal with name:" + cstmt.getString(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public RFood getRFoodByName(String name) {
        String selectSql ="SELECT * FROM rfoods WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);//pozitia parametrului din select

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToRFood(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateRFoodNameAndIngredients(String name, String new_name, String ingredients) {
        String updateNameSql = "UPDATE rfoods SET name=? and ingredients=? WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            // trebuie puse in functie de ordinea parametrilor
            preparedStatement.setString(1, new_name);
            preparedStatement.setString(2, ingredients);
            preparedStatement.setString(3, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RFood mapToRFood(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new RFood(resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4));
        }
        return null;
    }
    // PreparedStatement - use when we have parameters
    public void deleteRFoodByName(String name) {
        String deleteSql ="DELETE FROM rfoods WHERE name=?";

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
