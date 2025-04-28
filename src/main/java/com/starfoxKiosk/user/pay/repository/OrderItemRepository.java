package com.starfoxKiosk.user.pay.repository;

import com.starfoxKiosk.user.pay.domain.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.starfoxKiosk.common.JDBCTemplate.close;

public class OrderItemRepository {

    public int insertOrderItem(Connection con, OrderItem orderItem) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "INSERT INTO tbl_order_item (orderId, menuId, orderQuan, optionId) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getOptionId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        return result;
    }
}
