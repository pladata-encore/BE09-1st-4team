package com.starfoxKiosk.admin.view;

import com.starfoxKiosk.admin.controller.AdminController;
import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Order;

import java.util.List;
import java.util.Scanner;

public class AdminPage {

    private final AdminController ac = new AdminController();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n------------------------------");
            System.out.println("          관리자 메뉴         ");
            System.out.println("------------------------------");
            System.out.println("1. 상품 관리");
            System.out.println("2. 주문 내역 관리");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageProducts();
                    break;
                case "2":
                    manageOrders();
                    break;
                case "3":
                    // 종료 메시지 개선
                    System.out.println("\n------------------------------");
                    System.out.println("   관리자 콘솔을 종료합니다.   ");
                    System.out.println("   이용해 주셔서 감사합니다! ");
                    System.out.println("------------------------------");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void manageProducts() {
        while (true) {
            System.out.println("\n------------------------------");
            System.out.println("          상품 관리 메뉴      ");
            System.out.println("------------------------------");
            System.out.println("1. 상품 목록 조회");
            System.out.println("2. 상품 추가");
            System.out.println("3. 상품 수정");
            System.out.println("4. 상품 삭제");
            System.out.println("5. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ac.viewMenuItems();  // 상품 목록 조회
                    break;
                case "2":
                    addMenuItem();  // 상품 추가
                    break;
                case "3":
                    updateMenuItem();  // 상품 수정
                    break;
                case "4":
                    deleteMenuItem();  // 상품 삭제
                    break;
                case "5":
                    return;  // 이전 메뉴로 돌아가기
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void addMenuItem() {
        System.out.println("\n--- 상품 추가 ---");
        System.out.print("상품 이름: ");
        String name = scanner.nextLine();
        System.out.print("상품 가격: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("카테고리 ID: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        Menu newMenu = new Menu();
        newMenu.setName(name);
        newMenu.setPrice(price);
        newMenu.setCategoryId(categoryId);

        if (ac.addMenuItem(newMenu)) {
            System.out.println("상품이 성공적으로 추가되었습니다.");
        } else {
            System.out.println("상품 추가에 실패했습니다.");
        }
    }

    private void updateMenuItem() {
        System.out.println("\n--- 상품 수정 ---");
        System.out.print("수정할 상품 ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        // 수정할 상품 정보 가져오기
        Menu existingMenu = ac.getMenuItemById(id);
        if (existingMenu == null) {
            System.out.println("해당 상품이 존재하지 않습니다.");
            return;
        }

        System.out.print("새 상품 이름 (" + existingMenu.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("새 가격 (" + existingMenu.getPrice() + "): ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("새 카테고리 ID (" + existingMenu.getCategoryId() + "): ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        existingMenu.setName(name);
        existingMenu.setPrice(price);
        existingMenu.setCategoryId(categoryId);

        if (ac.updateMenuItem(existingMenu)) {
            System.out.println("상품 수정이 완료되었습니다.");
        } else {
            System.out.println("상품 수정에 실패했습니다.");
        }
    }

    private void deleteMenuItem() {
        System.out.println("\n--- 상품 삭제 ---");
        System.out.print("삭제할 상품 ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (ac.deleteMenuItem(id)) {
            System.out.println("상품 삭제가 완료되었습니다.");
        } else {
            System.out.println("상품 삭제에 실패했습니다.");
        }
    }

    private void manageOrders() {
        while (true) {
            System.out.println("\n------------------------------");
            System.out.println("      주문 내역 관리 메뉴    ");
            System.out.println("------------------------------");
            System.out.println("1. 대기 중인 주문 목록 조회");
            System.out.println("2. 제조 완료 목록 조회");
            System.out.println("3. 이전 메뉴로 돌아가기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewWaitingOrders();
                    break;
                case "2":
                    viewManufacturedOrders();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private void viewWaitingOrders() {
        List<Order> waitingOrders = ac.viewWaitingOrders();
        if (waitingOrders.isEmpty()) {
            System.out.println("\n------------------------------");
            System.out.println("  대기 중인 주문이 없습니다.");
            System.out.println("------------------------------");
        } else {
            System.out.println("\n--- 대기 중인 주문 목록 ---");
            for (int i = 0; i < waitingOrders.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, waitingOrders.get(i));
            }
            System.out.println("------------------------------");
            System.out.print("처리할 주문 번호를 선택하세요 (0: 이전 메뉴): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= waitingOrders.size()) {
                    int orderId = waitingOrders.get(choice - 1).getId();
                    markOrderAsManufactured(orderId);
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다. 숫자만 입력해주세요.");
            }
        }
    }

    private void markOrderAsManufactured(int orderId) {
        System.out.println("\n--- 제조 완료 처리 ---");
        if (ac.markOrderAsManufactured(orderId)) {
            System.out.println("주문 #" + orderId + "이(가) 제조 완료 처리되었습니다.");
        } else {
            System.out.println("주문 #" + orderId + " 제조 완료 처리에 실패했습니다.");
        }
    }

    private void viewManufacturedOrders() {
        List<Order> manufacturedOrders = ac.viewManufacturedOrders();
        if (manufacturedOrders.isEmpty()) {
            System.out.println("\n------------------------------");
            System.out.println("   제조 완료된 주문이 없습니다.  ");
            System.out.println("  (현재 처리된 주문이 없습니다) ");
            System.out.println("------------------------------");
        } else {
            System.out.println("\n--- 제조 완료된 주문 목록 ---");
            for (int i = 0; i < manufacturedOrders.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, manufacturedOrders.get(i));
            }
            System.out.println("------------------------------");
            System.out.print("처리할 주문 번호를 선택하세요 (0: 이전 메뉴): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 0 && choice <= manufacturedOrders.size()) {
                    int orderId = manufacturedOrders.get(choice - 1).getId();
                    markOrderAsPickupCompleted(orderId);
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 형식입니다. 숫자만 입력해주세요.");
            }
        }
    }

    private void markOrderAsPickupCompleted(int orderId) {
        System.out.println("\n--- 고객 픽업 완료 처리 ---");
        if (ac.markOrderAsPickupCompleted(orderId)) {
            System.out.println("주문 #" + orderId + "이(가) 고객 픽업 완료 처리되었습니다.");
        } else {
            System.out.println("주문 #" + orderId + " 고객 픽업 완료 처리에 실패했습니다.");
        }
    }
}
