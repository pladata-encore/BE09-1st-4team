package com.starfoxKiosk.user.pay.view;

import com.starfoxKiosk.user.pay.domain.Order;

import java.util.List;

public class OrderView {

    public void displayOrders(List<Order> orders) {
        System.out.println("=== 주문 목록 ===");
        for (Order order : orders) {
            System.out.println("주문 ID: " + order.getOrderId() + ", 고객 ID: " + order.getUserId());
        }
    }


    public void showOrderSuccess(int orderId) {
        System.out.println("주문이 성공적으로 접수되었습니다! 대기번호: " + orderId);
    }

    public void showOrderFail(int orderId) {
        System.out.println("주문에 실패했습니다. 다시 시도해주세요.");
    }
}
