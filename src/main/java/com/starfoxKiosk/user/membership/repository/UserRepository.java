package com.starfoxKiosk.user.membership.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.starfoxKiosk.common.JDBCTemplate.close;


public class UserRepository {

    public boolean isExistUser(Connection con, int customId){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean exists = false;

        try {
            String sql = "SELECT COUNT(*) FROM tbl_user WHERE customId=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, customId);
            rset = pstmt.executeQuery();

            if(rset.next()){
                exists =  rset.getInt(1)>0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
        }

        return exists;
    }



    public Integer findCustomIdByPhone(Connection con, String phone) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Integer customId = null;

        try {
            String sql = "SELECT customId FROM tbl_user WHERE phone = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, phone);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                customId = rset.getInt("customId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return customId;
    }

    public Integer insertUser(Connection con, String phone) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Integer customId = null;

        try {
            String sql = "INSERT INTO tbl_user (phone, total) VALUES (?, 0)";
            pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, phone);
            pstmt.executeUpdate();

            rset = pstmt.getGeneratedKeys();
            if (rset.next()) {
                customId = rset.getInt(1); // 새로 생성된 customId 반환
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        return customId;
    }


    public int updateTotal(Connection con, int customId, int total) {
        String sql = "UPDATE tbl_user SET total = ? WHERE customId = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, total);
            pstmt.setInt(2, customId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
