package com.starfoxKiosk.user.menu.controller;

import com.starfoxKiosk.user.menu.domain.Category;
import com.starfoxKiosk.user.menu.domain.Menu;
import com.starfoxKiosk.user.menu.domain.MenuWithOptions;
import com.starfoxKiosk.user.menu.domain.OrderDTO;
import com.starfoxKiosk.user.menu.service.MenuService;
import com.starfoxKiosk.user.menu.view.MenuView;
import com.starfoxKiosk.user.pay.controller.OrderController;

import java.util.List;

public class MenuController {

    private MenuView menuView;
    private MenuService menuService;
    private OrderController orderController;

    public MenuController() {
        this.menuView = new MenuView();
        this.menuService = new MenuService();
        this.orderController = new OrderController();
    }

    public void start() {

        List<Category> categories = menuService.getAllCategory();

        while (true) {
            int n = menuView.inputCategory(categories);

            switch (n) {
                case 0:
                    System.out.println("종료합니다.");
                    return;
                case 33:
                    selectCartMenu();
                    break;
                case 44:
                    //Todo
                    showOrder();
                    break;
                default:
                    selectedMenu(categories.get(n));
            }
        }
    }

    private void selectedMenu(Category category) {
        List<Menu> menus = menuService.getMenuByCategoryName(category.getName());

        MenuWithOptions menuWithOptions;
        int n = menuView.inputMenu(menus);
        switch (n) {
            case 0:
                return;
            default:
                menuWithOptions = getMenuWithOptionsById(menus.get(n - 1).getId());
                if (selectOption(menuWithOptions)) {
                    addCart(menuWithOptions);
                    System.out.println("장바구니에 추가되었습니다.");
                }
        }
    }

    private MenuWithOptions getMenuWithOptionsById(int menuId) {
        return menuService.getMenuWithOptinosById(menuId);
    }

    private boolean selectOption(MenuWithOptions menuWithOptions) {
        boolean result = menuView.selectOptions(menuWithOptions);
        return result;
    }

    private void addCart(MenuWithOptions menu) {
        menuService.addCart(menu);
    }

    private void selectCartMenu() {
        List<MenuWithOptions> cart = menuService.getCart();
        menuView.printCart(cart);
        int n = menuView.inputCartMenu();

        switch (n) {
            case 0:
                return;
            case 55:
                // 결제 페이지
                orderController.start(cart);
                break;
            default:
                modifyMenu(cart.get(n - 1), n - 1);
        }
    }

    private void modifyMenu(MenuWithOptions menuWithOptions, int index) {
        //삭제를 원한다.
        int n = menuView.inputModifyMenu(menuWithOptions);

        switch (n) {
            case 0:
                return;
            case 1:
                boolean result = selectOption(menuWithOptions);
                if (result) {
                    System.out.println("성공적으로 수정되었습니다.");
                }
                break;
            case 2:
                menuService.deleteCartMenu(index);
                break;
        }
    }

    public void showOrder() {
        List<OrderDTO> orders = menuService.getOrder();
        menuView.printOrder(orders);
    }
}
