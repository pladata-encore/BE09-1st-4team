package com.starfoxKiosk.user.pay.repository;

import com.starfoxKiosk.user.pay.domain.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.starfoxKiosk.common.JDBCTemplate.close;

public class OrderRepository {

    private final Properties prop;

    public OrderRepository() {

        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/starfoxKiosk/user/pay/mapper/OrderMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public int insertOrder(Connection con, Order order) {

        System.out.println(" 주문 ===> " + order);

        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("insertOrder");
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, order.getUserId()); // customId를 1번 파라미터로 세팅
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        System.out.println("result ===> " + result);
        return result;

    }

    public List<Order> findAllOrders(Connection con) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = "SELECT orderId, customId FROM tbl_order_history";

        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                int orderId = rset.getInt("orderId");
                int customId = rset.getInt("customId");
                orders.add(new Order(orderId, customId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        return orders;
    }
}
