package com.starfoxKiosk.user.pay.view;

import java.util.Scanner;

public class PaymentView {

    private final Scanner sc = new Scanner(System.in);

    public String selectPaymentMethod() {

        System.out.println("""
                결제 수단을 선택하세요. 
                1. 카드
                2. 간편결제
                3. 삼성페이
                0. 뒤로가기
                """);

        int choice = sc.nextInt();
        switch (choice) {

            case 1:
                return "카드";
            case 2:
                return "간편결제";
            case 3:
                return "삼성페이";
            case 0:
                return "뒤로가기";
            default:
                System.out.println("잘못된 선택입니다.다시 결제 선택해주세요.");
                return null;
        }


    }


    public void showResult(boolean b) {
        if (b) {
            System.out.println("✅결제가 완료되었습니다. 감사합니다.");
        }else {
            System.out.println("❌결제가 실패하였습니다.");
        }
    }
}
