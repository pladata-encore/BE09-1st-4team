package com.starfoxKiosk.user.menu.domain;

public class OrderDTO {

    private int id;
    private int customId;
    private String status;

    public OrderDTO(int id, int customId, String status) {
        this.id = id;
        this.customId = customId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        String s = "[";
        s += "order Id : " + id + ", ";
        s += "custom Id : " + id + ", ";
        if (status.equals("START")) {
            s += "준비중";
        }
        if (status.equals("COMPLETE")) {
            s += "완료됨";
        }
        s += "]";
        return s;
    }
}
