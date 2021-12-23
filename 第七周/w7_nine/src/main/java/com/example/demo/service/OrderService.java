package com.example.demo.service;

import com.example.demo.annotation.ReadOnly;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@Service
public class OrderService{

    @ReadOnly
    public void query() throws SQLException {
        DataSource dataSource =  DataBaseContextHolder.getDataSource();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            List<Map<String, Object>> entities = new ArrayList<>();
            conn = dataSource.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select id,order_no from t_order");
            while (resultSet.next()) {
                Map<String, Object> data = new HashMap<>(2);
                System.out.println(resultSet.getLong("id"));
                entities.add(data);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
