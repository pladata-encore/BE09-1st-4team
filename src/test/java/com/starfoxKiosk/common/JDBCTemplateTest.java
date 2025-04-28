package com.starfoxKiosk.common;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JDBCTemplateTest {

    @DisplayName("getConnection Test")
    @Test
    void getConnectionTest(){
        Connection con = JDBCTemplate.getConnection();

        assertNotNull(con);
    }
}