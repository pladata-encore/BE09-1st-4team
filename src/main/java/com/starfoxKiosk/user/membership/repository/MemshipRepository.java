package com.starfoxKiosk.user.membership.repository;

import com.starfoxKiosk.common.JDBCTemplate;
import com.starfoxKiosk.user.membership.domain.Membership;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MemshipRepository {

    private final Properties prop;

    public MemshipRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/starfoxKiosk/user/membership/mapper/MembershipMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Membership findByCustomId(Connection con,int customId) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Membership membership = null;


        try {
            String sql = prop.getProperty("findByCustomId");
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, customId);

            rset = pstmt.executeQuery();

            if(rset.next()) {
                int membershipId = rset.getInt("membershipId");
                String membershipName = rset.getString("membershipName");
                int defaultPrice = rset.getInt("defaultPrice");
                int foundCustomId = rset.getInt("customId");

                membership = new Membership(membershipId, membershipName, defaultPrice, foundCustomId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }
        return membership;


    }


    public int insertMembership(Connection con, Membership membership) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("insertMembership");
            pstmt=con.prepareStatement(sql);

            pstmt.setString(1, membership.getMembershipName());
            pstmt.setInt(2, membership.getDefaultPrice());
            pstmt.setInt(3, membership.getCustomId());


            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.close(pstmt);
        }
        return result;
    }

    public int updateMembership(Connection con, Membership membership) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("updateMembership");
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, membership.getMembershipName());
            pstmt.setInt(2, membership.getDefaultPrice());
            pstmt.setInt(3, membership.getCustomId());
            pstmt.setInt(4, membership.getMembershipId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.close(pstmt);
        }
        return result;
    }


    public int updateCustomId(Connection con, int oldCustomId, int newCustomId) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("updateCustomId");
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,newCustomId);
            pstmt.setInt(2,oldCustomId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.close(pstmt);
        }
        return result;

    }

    public int deleteMembership(Connection con, Membership membership) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("deleteMembership");
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, membership.getCustomId());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.close(pstmt);
        }
        return result;

    }

}


