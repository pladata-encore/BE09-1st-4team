package com.starfoxKiosk.user.membership.service;

import com.starfoxKiosk.user.membership.repository.UserRepository;

import java.sql.Connection;

import static com.starfoxKiosk.common.JDBCTemplate.close;
import static com.starfoxKiosk.common.JDBCTemplate.getConnection;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public boolean isExistUser(int customId) {
        Connection con = getConnection();
        boolean isExist = userRepository.isExistUser(con,customId);
        close(con);
        return  isExist;
    }

    public Integer findCustomIdByPhone(String phone) {
        Connection con = getConnection();
        Integer customId = userRepository.findCustomIdByPhone(con,phone);
        close(con);
        return customId;
    }
}
