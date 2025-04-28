package com.starfoxKiosk.user.menu.service;

import static com.starfoxKiosk.common.JDBCTemplate.getConnection;

import com.starfoxKiosk.user.menu.domain.Category;
import com.starfoxKiosk.user.menu.domain.Menu;
import com.starfoxKiosk.user.menu.domain.MenuWithOptions;
import com.starfoxKiosk.user.menu.domain.OrderDTO;
import com.starfoxKiosk.user.menu.repository.MenuRepository;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private MenuRepository menuRepository = new MenuRepository();
    private List<MenuWithOptions> cart = new ArrayList<>();

    public List<Category> getAllCategory() {
        Connection con = getConnection();

        List<Category> categories = menuRepository.getCategories(con);

        return categories;
    }

    public List<Menu> getMenuByCategoryName(String name) {
        Connection con = getConnection();

        List<Menu> menus = menuRepository.findMenuByCategoryName(con, name);

        return menus;
    }

    public MenuWithOptions getMenuWithOptinosById(int menuId) {
        Connection con = getConnection();
        MenuWithOptions menu = menuRepository.findMenuWithOptionsByID(con, menuId);

        return menu;
    }

    public void addCart(MenuWithOptions menuWithOptions) {
        cart.add(menuWithOptions);
    }

    public List<MenuWithOptions> getCart() {
        return cart;
    }

    public void deleteCartMenu(int i) {
        cart.remove(i);
    }

    public List<OrderDTO> getOrder() {
        Connection con = getConnection();
        List<OrderDTO> orders = menuRepository.findOrders(con);
        return orders;
    }
}
