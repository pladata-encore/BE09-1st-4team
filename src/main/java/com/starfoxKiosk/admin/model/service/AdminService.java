package com.starfoxKiosk.admin.model.service;

import com.starfoxKiosk.admin.model.dao.AdminRepository;
import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Order;
import com.starfoxKiosk.common.JDBCTemplate;

import java.sql.Connection;
import java.util.List;

import static com.starfoxKiosk.common.JDBCTemplate.close;

public class AdminService {

    private final AdminRepository adminRepository = new AdminRepository();

    // 상품 목록 조회
    public List<Menu> getAllMenuItems() {
        Connection conn = JDBCTemplate.getConnection();
        List<Menu> menuList = adminRepository.getAllMenuItems(conn);
        close(conn);
        return menuList;
    }

    // 상품 조회
    public Menu getMenuItemById(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        Menu menu = adminRepository.getMenuItemById(conn, menuId);
        close(conn);
        return menu;
    }

    // 상품 추가
    public boolean addMenuItem(Menu menuItem) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.addMenuItem(conn, menuItem);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }


    // 상품 수정
    public boolean updateMenuItem(Menu menu) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.updateMenuItem(conn, menu);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    // 상품 삭제
    public boolean deleteMenuItem(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.deleteMenuItem(conn, menuId);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    // 대기 중인 주문 조회
    public List<Order> getWaitingOrders() {
        Connection conn = JDBCTemplate.getConnection();
        List<Order> waitingOrders = adminRepository.getWaitingOrders(conn);
        close(conn);
        return waitingOrders;
    }

    // 제조 완료된 주문 조회
    public List<Order> getCompletedOrders() {
        Connection conn = JDBCTemplate.getConnection();
        List<Order> completedOrders = adminRepository.getManufacturedOrders(conn);
        close(conn);
        return completedOrders;
    }

    // 대기 중인 주문을 제조 완료 상태로 변경
    public boolean markOrderAsCompleted(int orderId) {
        Connection conn = JDBCTemplate.getConnection();
        boolean result = adminRepository.updateOrderStatusToManufactured(conn, orderId);
        boolean isSuccess = processResult(conn, result ? 1 : 0);
        close(conn);
        return isSuccess;
    }

    // 제조 완료된 주문을 고객 픽업 완료 상태로 변경
    public boolean markOrderAsPickupCompleted(int orderId) {
        Connection conn = JDBCTemplate.getConnection();
        boolean result = adminRepository.updateOrderStatusToPickupCompleted(conn, orderId);  // 픽업 완료 상태로 변경
        boolean isSuccess = processResult(conn, result ? 1 : 0);
        close(conn);
        return isSuccess;
    }

    // 트랜잭션 처리
    private boolean processResult(Connection conn, int result) {
        boolean isSuccess = false;
        if (result > 0) {
            JDBCTemplate.commit(conn);
            isSuccess = true;
        } else {
            JDBCTemplate.rollback(conn);
        }
        close(conn);
        return isSuccess;
    }
}
