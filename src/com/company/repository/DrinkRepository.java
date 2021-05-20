package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.product.Drink;

import java.sql.*;

public class DrinkRepository {
    // CallableStatement
    public void insertDrink(Drink drink) {
        String preparedSql = "{call insertDrink(?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, drink.getName());
            cstmt.setDouble(2, drink.getPrice());
            cstmt.setString(3, drink.getFlavour());

            cstmt.registerOutParameter(1, Types.VARCHAR ); //out param (result of the procedure call)

            cstmt.execute();
            System.out.println("Added drink with name:" + cstmt.getString(1));    //out param (result of the procedure call)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Drink getDrinkByName(String name) {
        String selectSql ="SELECT * FROM drinks WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);//pozitia parametrului din select

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDrink(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateDrinkPrice(String name, Double price) {
        String updateNameSql = "UPDATE drinks SET price=? WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            // trebuie puse in functie de ordinea parametrilor
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Drink mapToDrink(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Drink(resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4));
        }
        return null;
    }
}
