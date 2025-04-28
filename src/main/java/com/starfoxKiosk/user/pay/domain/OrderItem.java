package com.starfoxKiosk.user.pay.domain;

public class OrderItem {
    private int orderId;
    private int menuId;
    private int quantity;
    private Integer optionId; // null 허용

    public OrderItem() {}

    public OrderItem(int orderId, int menuId, int quantity, Integer optionId) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.optionId = optionId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", menuId=" + menuId +
                ", quantity=" + quantity +
                ", optionId=" + optionId +
                '}';
    }


}
