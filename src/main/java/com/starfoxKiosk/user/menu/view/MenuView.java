package com.starfoxKiosk.user.menu.view;

import com.starfoxKiosk.user.menu.domain.Category;
import com.starfoxKiosk.user.menu.domain.Menu;
import com.starfoxKiosk.user.menu.domain.MenuWithOptions;
import com.starfoxKiosk.user.menu.domain.Option;
import com.starfoxKiosk.user.menu.domain.OrderDTO;
import java.util.List;
import java.util.Scanner;

public class MenuView {

    private Scanner sc = new Scanner(System.in);

    public int inputCategory(List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            sb.append(i + 1).append(".").append(categories.get(i).getName());
            sb.append("\n");
        }
        sb.append("33.장바구니\n");
        sb.append("44.대기목록\n");
        sb.append("0.종료\n");
        sb.append("번호를 입력하세요 : ");
        System.out.print(sb);
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public int inputMenu(List<Menu> menus) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menus.size(); i++) {
            sb.append(i + 1).append(".").append(menus.get(i).getName());
            sb.append(", 가격 : ").append(menus.get(i).getPrice());
            sb.append("\n");
        }
        sb.append("0.뒤로가기\n");
        sb.append("번호를 입력하세요 : ");
        System.out.print(sb);
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public boolean selectOptions(MenuWithOptions menuWithOptions) {

        for (int j = 0; j < menuWithOptions.getOptions().size(); j++) {
            Option option = menuWithOptions.getOptions().get(j);
            System.out.println(option.getName());
            for (int i = 0; i < option.getTypes().size(); i++) {
                System.out.printf("%d.%s\n", i + 1, option.getTypes().get(i));
            }
            System.out.print("옵션을 선택하세요 : ");

            int n = sc.nextInt();
            sc.nextLine();
            if (n == 0) {
                j--;
                if (j < 0) {
                    return false;
                }
            }
            option.setSelectedType(option.getTypes().get(n - 1));
        }
        System.out.print("개수를 입력하세요 : ");
        menuWithOptions.setCount(sc.nextInt());
        menuWithOptions.setTotalPrice(menuWithOptions.getCount() * menuWithOptions.getPrice());
        sc.nextLine();

        System.out.print("사이즈를 선택하세요 (R, L) : ");
        menuWithOptions.setSize(sc.nextLine());
        return true;
    }


    public void printCart(List<MenuWithOptions> cart) {
        for (int i = 0; i < cart.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.printf(cart.get(i).toString());
        }
    }

    public int inputCartMenu() {
        System.out.println("0. 뒤로");
        System.out.println("55. 결제");
        System.out.print("번호를 입력하세요 : ");
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public int inputModifyMenu(MenuWithOptions menu) {
        System.out.printf(menu.toString());
        System.out.println("0. 뒤로");
        System.out.println("1. 변경");
        System.out.println("2. 삭제");
        System.out.print("번호를 입력하세요 : ");

        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }


    public void printOrder(List<OrderDTO> list) {
        for (OrderDTO order : list) {
            System.out.println(order);
        }
        System.out.println("아무거나 입력해주세요 : ");
        String s = sc.nextLine();
    }

}
