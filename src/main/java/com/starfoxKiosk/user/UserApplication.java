package com.starfoxKiosk.user;

import com.starfoxKiosk.user.menu.controller.MenuController;

public class UserApplication {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        menuController.start();
    }
}
