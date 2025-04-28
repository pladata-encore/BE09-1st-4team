package com.starfoxKiosk.admin.model.dto;

public class Order {
    private int id;
    private String customerPhoneNumberSuffix;
    private String status;

    public Order(int id, String customerPhoneNumberSuffix, String status) {
        this.id = id;
        this.customerPhoneNumberSuffix = customerPhoneNumberSuffix;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCustomerPhoneNumberSuffix() {
        return customerPhoneNumberSuffix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("주문 ID: %d, 고객 전화번호 뒷자리: %s, 상태: %s", id, customerPhoneNumberSuffix, status);
    }
}
