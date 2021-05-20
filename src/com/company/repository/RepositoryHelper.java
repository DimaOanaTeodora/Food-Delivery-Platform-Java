package com.company.repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryHelper { //singleton ?

    private static RepositoryHelper repositoryHelper = new RepositoryHelper();

    private RepositoryHelper() {}

    public static RepositoryHelper getRepositoryHelper() {
        return repositoryHelper;
    }

    public void executeSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        // execute() for updating (INSERT, UPDATE, DELETE) and SELECT instructions
        stmt.execute(sql); //returneaza un boolean
        //ResultSet resultSet = stmt.getResultSet(); // pt SELECT-URI
    }

    public void executeUpdateSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        // executeUpdate() for updating the data (INSERT, UPDATE, DELETE) or the database structure
        int i = stmt.executeUpdate(sql); // number of altered lines
    }

    public ResultSet executeQuerySql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        // executeQuery() for SELECT instructions
        return stmt.executeQuery(sql);
    }

}
