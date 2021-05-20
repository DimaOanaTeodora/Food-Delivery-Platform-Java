package com.company.config;

import com.company.repository.RepositoryHelper;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class DataSetup {

    public void createTables() {
        File file = new File("Files/QuerysCreateTables.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String createTableSql;
            while ((createTableSql = br.readLine()) != null){
                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeSql(databaseConnection, createTableSql);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error when I try create the tables for the database !");
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addRows() {

        File file = new File("Files/QuerysAddRows.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String insertRowSql;
            while ((insertRowSql = br.readLine()) != null){

                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeUpdateSql(databaseConnection, insertRowSql);
                }  catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error when I try insert rows into the tables !");
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteAllRows(){
        File file = new File("Files/QuerysDeleteAllRows.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String deleteRowsSql;
            while ((deleteRowsSql = br.readLine()) != null){

                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeUpdateSql(databaseConnection, deleteRowsSql);
                }  catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error when I try to delete all rows from tables !");
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dropAllTables() {
        File file = new File("Files/QuerysDropAllTables.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String dropTableSql;
            while ((dropTableSql = br.readLine()) != null){
                Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeSql(databaseConnection, dropTableSql);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error when I try create the tables for the database !");
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void displayTable() {
        String selectSql="";
        System.out.print("Give the name of the table do you want to show:");
        Scanner var= new Scanner(System.in);
        String tableName= var.nextLine();

        if (tableName.equalsIgnoreCase("burgers"))
            selectSql ="SELECT * FROM burgers";
        else if (tableName.equalsIgnoreCase("sweets"))
            selectSql ="SELECT * FROM sweets";
        else if (tableName.equalsIgnoreCase("drinks"))
            selectSql ="SELECT * FROM drinks";
        else if (tableName.equalsIgnoreCase("rfoods"))
            selectSql ="SELECT * FROM rfoods";


        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                for(int i=2; i<=columnsNumber;i++)//coloana 1 este id-ul
                    System.out.print(resultSet.getString(i)+" ");
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}