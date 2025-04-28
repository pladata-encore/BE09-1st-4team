package com.starfoxKiosk.user.membership.controller;

import com.starfoxKiosk.user.membership.domain.Membership;
import com.starfoxKiosk.user.membership.service.MemshipService;
import com.starfoxKiosk.user.membership.view.MemshipView;
import com.starfoxKiosk.user.membership.service.UserService;

import java.util.Scanner;

public class MembershipController {

    private MemshipService memshipService;
    private MemshipView memshipView;
    private UserService userService;

    public MembershipController() {
        this.memshipService = new MemshipService();
        this.memshipView =  new MemshipView();
        this.userService = new UserService();
    }



    public Membership start() {
        int customId = memshipView.inputCustomId();
        Membership membership = memshipService.findByCustomId(customId);

        if (membership != null) {
            memshipView.displayMembership(membership);

            if(memshipView.inputUseMembership()){
                System.out.println("멤버십을 사용합니다.");
                return membership;
            }else {
                System.out.println("멤버십을 사용하지 않습니다.");
                return null;
            }

        } else {
            System.out.println("해당 번호로 등록된 멤버십 정보가 없습니다.");
            if(memshipView.inputUseMembership()){
                Membership newMem = memshipView.inputNewMembership(customId);
                int total = newMem.getDefaultPrice();
                String phone = "010" + String.format("%08d", customId);
                memshipService.registerUserAndMembership(phone, total, newMem);

                System.out.println("✅멤버십이 등록되었습니다.");
                return newMem;
            } else{
                System.out.println("뒤로 이동합니다.");
                return null;
            }
        }


    }

    public void registMembership(){
        int customId = memshipView.inputCustomId();
        String phone = "010" + String.format("%08d",customId);

        if (!userService.isExistUser(customId)) {
            System.out.println("회원가입이 되어 있지 않은 번호입니다. 먼저 회원가입을 진행해주세요.");
            return;
        }

        // (회원가입 되어있으면)
        Membership newMembership = memshipView.inputNewMembership(customId);

        int total = newMembership.getDefaultPrice();//누적 금액

        // registerUserAndMembership() 호출해서 user+membership 모두 등록
        memshipService.registerUserAndMembership(phone,total,newMembership);
        System.out.println("멤버십이 성공적으로 등록되었습니다.");
    }


    public  void updateMembership(){
        int customId = memshipView.inputCustomId();
        Membership modifiedMembership = memshipView.updateMembership(); // 수정할 정보 입력
        memshipService.updateMembership(modifiedMembership); // 수정 실행
        System.out.println("멤버십 정보가 성공적으로 수정되었습니다.");
    }

    public void updateMembershipFlow(){
        System.out.println("1. 전화번호만 수정");
        System.out.println("2. 누적금액 & 등급 수정");
        System.out.print("선택: ");
        String choice = new Scanner(System.in).nextLine();

        if(choice.equals("1")) {
            int oldCustomId = memshipView.inputOldCustomId();
            int newCustomId = memshipView.inputNewCustomId();


            Membership existing = memshipService.findByCustomId(oldCustomId);
            if (existing == null) {
                System.out.println("해당 멤버십이 존재하지 않습니다.");
                return;
            }
            memshipService.updateCustomId(oldCustomId, newCustomId);
            System.out.println("전화번호가 수정되었습니다.");


        }else if(choice.equals("2")) {
            Membership modified = memshipView.updateMembership();
            memshipService.updateMembership(modified);
            System.out.println("멤버십 정보가 수정되었습니다.");
        }else{
            System.out.println("잘못된 선택입니다.");

        }


    }

}
