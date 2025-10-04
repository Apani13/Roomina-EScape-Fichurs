package cat.itacademy;

import cat.itacademy.presentation.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MainMenu mainMenu = new MainMenu();

        mainMenu.showAvailableInventory();
        mainMenu.showTotalInventoryPrice();
        mainMenu.showTotalValueOfSales();

    }
}