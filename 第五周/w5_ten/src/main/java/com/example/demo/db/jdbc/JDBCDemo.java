package com.example.demo.db.jdbc;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


public class JDBCDemo {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Root123");
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }


    /**
     * 关闭连接
     *
     * @throws SQLException
     */
    private void closeConn() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        System.out.println("Connection close");
    }


    /**
     * 插入
     *
     * @param tableName
     * @param cols
     * @param values
     * @return
     */
    public boolean insertData(String tableName, List<String> cols, List<Object> values) {
        try {
            String insertTemplate = this.buildInsertSQL(tableName, cols, values.size());
            preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i = 1; i < values.size() + 1; i++) {
                preparedStatement.setObject(i, values.get(i - 1));
            }

            //preparedStatement.execute();

            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            System.out.println("success");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void queryData(String table) {
        try {
            String sqlTemplate = this.buildQuerySql(table);

            preparedStatement = connection.prepareStatement(sqlTemplate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String buildInsertSQL(String tableName, List<String> cols, int valuesCount) {
        String column = "(" + cols.stream().collect(Collectors.joining(",")).toString() + ")";

        String placeHolders = String.join(",", Collections.nCopies(valuesCount, "?"));
        return "insert into " + tableName + " " + column + " values (" + placeHolders + ")";
    }

    public String buildQuerySql(String tableName) {
        String sqlTemplate = "select * from " + tableName;

        return sqlTemplate;
    }

    public static void main(String[] args) throws Exception {

        JDBCDemo jdbcDemo = null;
        try {
            jdbcDemo = new JDBCDemo();
            jdbcDemo.createConnection();

            int uid = new Random().nextInt();

            String table = "user";
            List<String> columnsList = Arrays.asList("uid", "name");

            List<Object> values = Arrays.asList(uid, "jack" + uid);
            jdbcDemo.getConnection().setAutoCommit(false);
            jdbcDemo.insertData(table, columnsList, values);

            jdbcDemo.getConnection().commit();

            jdbcDemo.queryData(table);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcDemo.closeConn();
        }
    }

}

