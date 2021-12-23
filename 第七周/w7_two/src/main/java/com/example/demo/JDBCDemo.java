package com.example.demo;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class JDBCDemo {

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

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


    public static Connection getConnection() {
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


    public void queryData(String table) {
        try {
            String sqlTemplate = this.buildQuerySql(table);

            preparedStatement = connection.prepareStatement(sqlTemplate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            long time = System.currentTimeMillis();

            connection.setAutoCommit(false);
            time = System.currentTimeMillis();
            String insertTemplate = "insert into t_order (buyer_id,order_no,trade_type,trade_status) values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i = 0; i < 1000000; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, i + "");
                preparedStatement.setInt(3, 1);
                preparedStatement.setInt(4, 2);
                preparedStatement.addBatch();
                if (i % 1000 == 0 && i != 0 || i == (1000000 - 1)) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();

                }
            }

            connection.commit();

            System.out.println("cost time=" + (System.currentTimeMillis() - time) + " ms");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcDemo.closeConn();
        }
    }

}

