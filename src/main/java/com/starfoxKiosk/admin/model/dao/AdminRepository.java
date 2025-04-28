package com.starfoxKiosk.admin.model.dao;

import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    // 상품 목록 조회
    public List<Menu> getAllMenuItems(Connection conn) {
        List<Menu> menuItems = new ArrayList<>();
        String sql = "SELECT `menuId`, `menuName`, `menuPrice`, `categoryId` FROM tbl_menu";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                menuItems.add(new Menu(rs.getInt("menuId"), rs.getString("menuName"), rs.getInt("menuPrice"), rs.getInt("categoryId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // 대기 중인 주문 조회
    public List<Order> getWaitingOrders(Connection conn) {
        List<Order> waitingOrders = new ArrayList<>();
        String sql = "SELECT oh.`orderId`, u.`phone` " +
                "FROM `tbl_order_history` oh JOIN `tbl_user` u ON oh.`customId` = u.`customId` " +
                "WHERE oh.`orderId` IN (SELECT `orderId` FROM `tbl_order_item` WHERE `status` = '대기중')";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String fullPhoneNumber = rs.getString("phone");
                String suffix = (fullPhoneNumber != null && fullPhoneNumber.length() >= 4) ? fullPhoneNumber.substring(fullPhoneNumber.length() - 4) : "";
                waitingOrders.add(new Order(rs.getInt("orderId"), suffix, "대기중"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waitingOrders;
    }

    // 제조 완료 목록 조회
    public List<Order> getManufacturedOrders(Connection conn) {
        List<Order> manufacturedOrders = new ArrayList<>();
        String sql = "SELECT oh.`orderId`, u.`phone` " +
                "FROM `tbl_order_history` oh JOIN `tbl_user` u ON oh.`customId` = u.`customId` " +
                "WHERE oh.`orderId` IN (SELECT `orderId` FROM `tbl_order_item` WHERE `status` = '제조완료')";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String fullPhoneNumber = rs.getString("phone");
                String suffix = (fullPhoneNumber != null && fullPhoneNumber.length() >= 4) ? fullPhoneNumber.substring(fullPhoneNumber.length() - 4) : "";
                manufacturedOrders.add(new Order(rs.getInt("orderId"), suffix, "제조완료"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturedOrders;
    }

    // 대기 중인 주문을 제조 완료로 변경
    public boolean updateOrderStatusToManufactured(Connection conn, int orderId) {
        String sql = "UPDATE `tbl_order_item` SET `status` = '제조완료' WHERE `orderId` = ? AND `status` = '대기중'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 제조 완료된 주문을 고객 픽업 완료 상태로 변경
    public boolean updateOrderStatusToPickupCompleted(Connection conn, int orderId) {
        String sql = "UPDATE `tbl_order_item` SET `status` = '픽업완료' WHERE `orderId` = ? AND `status` = '제조완료'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 상품 조회
    public Menu getMenuItemById(Connection conn, int menuId) {
        String sql = "SELECT `menuId`, `menuName`, `menuPrice`, `categoryId` FROM tbl_menu WHERE `menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Menu(rs.getInt("menuId"), rs.getString("menuName"), rs.getInt("menuPrice"), rs.getInt("categoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 상품 추가
    public int addMenuItem(Connection conn, Menu menu) {
        int result = 0;
        String sql = "INSERT INTO tbl_menu (`menuName`, `menuPrice`, `categoryId`) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menu.getName());
            pstmt.setInt(2, menu.getPrice());
            pstmt.setInt(3, menu.getCategoryId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    // 상품 수정
    public int updateMenuItem(Connection conn, Menu menu) {
        int result = 0;
        String sql = "UPDATE tbl_menu SET `menuName` = ?, `menuPrice` = ?, `categoryId` = ? WHERE `menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menu.getName());
            pstmt.setInt(2, menu.getPrice());
            pstmt.setInt(3, menu.getCategoryId());
            pstmt.setInt(4, menu.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 상품 삭제
    public int deleteMenuItem(Connection conn, int menuId) {
        int result = 0;
        String sql = "DELETE FROM tbl_menu WHERE `menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
