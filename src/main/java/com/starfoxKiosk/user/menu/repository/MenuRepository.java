package com.starfoxKiosk.user.menu.repository;

import com.starfoxKiosk.common.JDBCTemplate;
import com.starfoxKiosk.user.menu.domain.Category;
import com.starfoxKiosk.user.menu.domain.Menu;
import com.starfoxKiosk.user.menu.domain.MenuWithOptions;
import com.starfoxKiosk.user.menu.domain.OrderDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MenuRepository {

    private Properties prop;

    public MenuRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream(
                "src/main/java/com/starfoxKiosk/user/menu/mapper/MenuMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getCategories(Connection con) {
        String sql = prop.getProperty("selectCategories");
        List<Category> categories = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(pstmt);
        }

        return categories;
    }

    public List<Menu> findMenuByCategoryName(Connection con, String name) {
        String sql = prop.getProperty("selectMenuByCategoryName");
        List<Menu> menus = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int menuId = rs.getInt(1);
                String menuName = rs.getString(2);
                String categoryName = rs.getString(3);
                int categoryId = rs.getInt(4);
                int price = rs.getInt(5);
                menus.add(new Menu(menuId, menuName, categoryName, categoryId, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(pstmt);
        }

        return menus;
    }

    public MenuWithOptions findMenuWithOptionsByID(Connection con, int id) {
        String menuSql = prop.getProperty("selectMenuById");
        String optionSql = prop.getProperty("selectOptionByMenuId");
        MenuWithOptions menuWithOptions = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(menuSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int menuId = rs.getInt(1);
                String menuName = rs.getString(2);
                String categoryName = rs.getString(3);
                int categoryId = rs.getInt(4);
                int price = rs.getInt(5);

                menuWithOptions = new MenuWithOptions(menuId, menuName, categoryName, categoryId,
                    price);
            }
            /*
            pstmt = con.prepareStatement(optionSql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String optionName = rs.getString(3);
                String optionType = rs.getString(4);
                menuWithOptions.addOption(optionName, optionType);
            }
            */

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(pstmt);
        }

        return menuWithOptions;
    }

    public List<OrderDTO> findOrders(Connection con) {
        String sql = prop.getProperty("selectOrder");
        List<OrderDTO> orders = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customId = rs.getInt(2);
                String status = rs.getString(3);

                orders.add(new OrderDTO(orderId, customId, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(pstmt);
        }

        return orders;
    }
}
