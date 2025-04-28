package com.starfoxKiosk.user.membership.view;

import com.starfoxKiosk.user.membership.domain.Membership;

import java.util.Scanner;

public class MemshipView {

    private Scanner sc = new Scanner(System.in);

    // 등급 결정 로직을 분리
    private String determineMembershipName(int total) {
        if (total > 100000) {
            return "PLATINUM";
        } else if (total >= 50000) {
            return "GOLD";
        } else if (total >= 10000) {
            return "SILVER";
        } else {
            return "BRONZE";
        }
    }

    // 전화번호 입력 받는 로직
    public int inputCustomId(){
        System.out.print("전화번호를 입력하세요 (⚠️숫자 8자리만) : ");
        String input = sc.nextLine();

        if(!input.matches("\\d{1,8}")){
            System.out.println(" ⚠️ 010을 뺀 나머지 8자리 숫자만 입력해주세요.");
            return inputCustomId();
        }
        return Integer.parseInt(input);
    }


    // 📋 정수 입력 공통 처리
    private int inputInt() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("⚠️ 숫자만 입력해주세요. 다시 입력하세요: ");
            }
        }
    }


    public void displayMembership(Membership membership){
        System.out.println("멤버십 등급 : " + membership.getMembershipName());
        System.out.println("누적 금액 : " + membership.getDefaultPrice() + " 원");
    }


    public boolean inputUseMembership(){
        System.out.print("멤버십을 사용하시겠습니까? (Y/N)");
        String answer = sc.nextLine().trim();
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
    }

    //전화번호만 바꾸는 메서드
    public int inputOldCustomId(){
        System.out.print("기존 전화번호를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int inputNewCustomId(){
        System.out.print("새로운 전화번호를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }


    // 📋 새 멤버십 정보 입력받기 (누적금액 → 등급 자동 결정)
    public Membership inputNewMembership(int customId){

        System.out.println("=== 새 멤버십 등록 ===");

        System.out.print("누적금액을 입력하세요: ");
        int defaultPrice = Integer.parseInt(sc.nextLine());

        //누적 금액에 따라 멤버십 등급 결정
        String membershipName = determineMembershipName(defaultPrice);
        System.out.println("✅ 등급이 자동 설정되었습니다: " + membershipName);

        return new Membership(0,membershipName,defaultPrice,customId);
    }

    public Membership updateMembership(){

        System.out.println("=== 멤버십 정보 수정 ===");

        System.out.print("수정할 전화번호(8자리)를 입력하세요 : ");
        int customId = Integer.parseInt(sc.nextLine());

        System.out.print("새로운 누적금액을 입력하세요: ");
        int defaultPrice = Integer.parseInt(sc.nextLine());

        String membershipName = determineMembershipName(defaultPrice);

        System.out.println("✅ 새로운 멤버십 등급: " + membershipName);

        return  new Membership(0,membershipName,defaultPrice,customId);
    }

    // 📋 삭제할 멤버십 ID 입력받기
    public int inputDeleteMembershipId() {
        System.out.println("=== 멤버십 삭제 ===");
        System.out.print("삭제할 멤버십 ID를 입력하세요: ");
        return inputInt();
    }




}
