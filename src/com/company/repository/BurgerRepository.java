package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.product.Burger;

import java.sql.*;

public class BurgerRepository {

    // CallableStatement
    public void insertBurger(Burger burger) {
        String preparedSql = "{call insertBurger(?,?,?,?)}";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, burger.getName());
            cstmt.setDouble(2, burger.getPrice());
            cstmt.setBoolean(3, burger.getisVegan());
            cstmt.setString(4, burger.getIngredients());

            //out param (result of the procedure call)
            cstmt.registerOutParameter(1, Types.VARCHAR );

            cstmt.execute();

            //out param (result of the procedure call)
            System.out.println(">---Added burger with name: " + cstmt.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement - use when we have parameters
    public Burger getBurgerByName(String name) {
        String selectSql ="SELECT * FROM burgers WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);//pozitia parametrului din select

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToBurger(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement
    public void updateBurgerIngredients(String name, String ingredients) {
        String updateNameSql = "UPDATE burgers SET ingredients=? WHERE name=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);

            // trebuie puse in functie de ordinea parametrilor
            preparedStatement.setString(1, ingredients);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Burger mapToBurger(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Burger(resultSet.getString(2), resultSet.getDouble(3), resultSet.getBoolean(4),resultSet.getString(5));
        }
        return null;
    }

    // PreparedStatement - use when we have parameters
    public void deleteBurgerByName(String name) {
        String deleteSql ="DELETE FROM burgers WHERE name=?";

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
