package com.starfoxKiosk.user.membership.service;

import com.starfoxKiosk.user.membership.domain.Membership;
import com.starfoxKiosk.user.membership.repository.MemshipRepository;
import com.starfoxKiosk.user.membership.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

import static com.starfoxKiosk.common.JDBCTemplate.*;

public class MemshipService {


    private final UserRepository userRepository = new UserRepository();
    private final MemshipRepository membershipRepository= new MemshipRepository();

    public Membership findByCustomId(int customId) {
        Connection con = getConnection();
        Membership membership = membershipRepository.findByCustomId(con,customId);
        close(con);
        return membership;
    }

    public Membership registMembership(Membership membership) {
        Connection con = getConnection();
        int result = membershipRepository.insertMembership(con,membership);

        if(result >0){
            commit(con);
        }else {
            rollback(con);
        }
        close(con);
        return membership;
    }

    public Membership updateMembership(Membership membership) {
        Connection con = getConnection();
        int result = membershipRepository.updateMembership(con,membership);

        if(result >0){
            commit(con);
        }else {
            rollback(con);
        }
        close(con);
        return membership;
    }

    public void updateCustomId(int oldCustomId, int newCustomId) {
        Connection con = getConnection();
        int result = membershipRepository.updateCustomId(con, oldCustomId, newCustomId);

        if(result >0){
            commit(con);
        }else{
            rollback(con);
        }
        close(con);
    }


    public void registerUserAndMembership(String phone, int total, Membership membership) {
        Connection con = getConnection();

        try {
            // 1. 트랜잭션 시작
            con.setAutoCommit(false);

            // 2. phone으로 user 조회
            Integer customId = userRepository.findCustomIdByPhone(con, phone);

            // 3. user 없으면 생성
            if (customId == null) {
                customId = userRepository.insertUser(con, phone); // user 등록 후 생성된 customId 가져오기
                System.out.println("✅ 사용자 등록 완료: customId = " + customId);
                if (customId == null) {
                    throw new RuntimeException("사용자 등록 실패! customId를 생성하지 못했습니다.");
                }
            }

            // 3. total 입력받아서 user total 업데이트
            userRepository.updateTotal(con, customId, total);

            // 4. membership 객체에 customId 세팅
            membership.setCustomId(customId);

            // 5. membership 테이블에 insert

            membershipRepository.insertMembership(con, membership);
            System.out.println("✅ 멤버십 등록 완료: membershipName = " + membership.getMembershipName());

            // 6. 모두 성공했으면 커밋
            con.commit();
            System.out.println("✅ 트랜잭션 커밋 완료");

        } catch (Exception e) {
            try {
                con.rollback();
                System.out.println("⚠️ 트랜잭션 롤백 완료");
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("롤백 중 에러 발생", rollbackEx);
            }
            throw new RuntimeException("회원가입 중 에러 발생", e);

        } finally {
            try {
                con.setAutoCommit(true); // 트랜잭션 복구
            } catch (SQLException e) {
                throw new RuntimeException("AutoCommit 복구 중 에러 발생", e);
            }
            close(con);
        }
    }

}
