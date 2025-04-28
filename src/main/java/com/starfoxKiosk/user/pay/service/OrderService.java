package com.starfoxKiosk.user.pay.service;

import com.starfoxKiosk.common.JDBCTemplate;
import com.starfoxKiosk.user.pay.domain.Order;
import com.starfoxKiosk.user.pay.repository.OrderRepository;

import java.sql.Connection;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository = new OrderRepository();

    public void register(Order order) {
        Connection con = JDBCTemplate.getConnection();

        int result = orderRepository.insertOrder(con,order);
        if(result > 0){
            JDBCTemplate.commit(con);
        }else {
            JDBCTemplate.rollback(con);
        }
        JDBCTemplate.close(con);
    }

    public void insert(Order order) {
        Connection con = JDBCTemplate.getConnection();

        int result = orderRepository.insertOrder(con,order);

        if(result > 0){
            JDBCTemplate.commit(con);
        }else{
            JDBCTemplate.rollback(con);
        }
        JDBCTemplate.close(con);
    }

    public List<Order> getOrders() {
        Connection con = JDBCTemplate.getConnection();
        List<Order> orders = orderRepository.findAllOrders(con);
        JDBCTemplate.close(con);
        return orders;
    }

}
