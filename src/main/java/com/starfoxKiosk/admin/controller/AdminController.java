package com.starfoxKiosk.admin.controller;

import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Order;
import com.starfoxKiosk.admin.model.service.AdminService;

import java.util.List;

public class AdminController {

    private final AdminService adminService = new AdminService();

    // 상품 목록 조회
    public void viewMenuItems() {
        List<Menu> menuList = adminService.getAllMenuItems();
        if (menuList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
        } else {
            System.out.println("\n--- 상품 목록 ---");
            for (Menu menu : menuList) {
                System.out.println(menu);
            }
            System.out.println("------------------");
        }
    }

    // 대기 중인 주문 목록 조회
    public List<Order> viewWaitingOrders() {
        return adminService.getWaitingOrders();
    }

    // 대기 중인 주문을 제조 완료 상태로 변경
    public boolean markOrderAsManufactured(int orderId) {
        return adminService.markOrderAsCompleted(orderId);  // AdminService의 markOrderAsCompleted 호출
    }

    // 제조 완료된 주문 목록 조회
    public List<Order> viewManufacturedOrders() {
        return adminService.getCompletedOrders();
    }

    // 제조 완료된 주문을 고객 픽업 완료로 변경
    public boolean markOrderAsPickupCompleted(int orderId) {
        return adminService.markOrderAsPickupCompleted(orderId);  // AdminService의 markOrderAsPickupCompleted 호출
    }

    // 특정 상품 조회
    public Menu getMenuItemById(int id) {
        return adminService.getMenuItemById(id);
    }

    // 상품 추가
    public boolean addMenuItem(Menu menu) {
        return adminService.addMenuItem(menu);
    }

    // 상품 수정
    public boolean updateMenuItem(Menu menu) {
        return adminService.updateMenuItem(menu);
    }

    // 상품 삭제
    public boolean deleteMenuItem(int menuId) {
        return adminService.deleteMenuItem(menuId);
    }

    // 특정 메뉴의 옵션 조회 (추후 고도화 예정)
    /*
    public List<Option> getOptionsForMenu(Menu menu) {
        return adminService.getOptionsForMenu(menu.getId());
    }
    */

    // 옵션 추가 (추후 고도화 예정)
    /*
    public boolean addOption(Option option) {
        return adminService.addOption(option);
    }
    */

    // 옵션 수정 (추후 고도화 예정)
    /*
    public boolean updateOption(Option option) {
        return adminService.updateOption(option);
    }
    */

    // 옵션 삭제 (추후 고도화 예정)
    /*
    public boolean deleteOption(int optionId) {
        return adminService.deleteOption(optionId);
    }
    */

    // 대기 중인 주문 조회
    public List<Order> getWaitingOrders() {
        return adminService.getWaitingOrders();
    }
}
