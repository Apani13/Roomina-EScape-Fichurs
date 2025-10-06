package cat.itacademy.presentation;

import java.util.Scanner;

public class NotificationMenu {
    private Scanner sc;

    public NotificationMenu(Scanner sc) {
        this.sc = sc;
    }

    public void showNotificationsMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            ConsoleUtils.clearScreen();
            System.out.println("🔔 GESTIÓN DE NOTIFICACIONES");
            System.out.println("1. Enviar Notificaciones a Usuarios");
            System.out.println("2. Emitir Certificado de Superación");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");

            int option = readOption();
            backToMain = processNotificationsOption(option);
        }
    }

    private boolean processNotificationsOption(int option) {
        switch (option) {
            case 1:
                sendNotifications();
                break;
            case 2:
                issueCertificate();
                break;
            case 0:
                return true;
            default:
                System.out.println("❌ Opción no válida");
        }
        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }

    private void sendNotifications() {
        System.out.println("\n🔔 ENVIAR NOTIFICACIONES A USUARIOS");
        System.out.print("Mensaje de la notificación: ");
        String message = sc.nextLine();
        System.out.println("✅ Notificación enviada: " + message);
    }

    private void issueCertificate() {
        System.out.println("\n🏆 EMITIR CERTIFICADO DE SUPERACIÓN");
        System.out.print("Nombre del jugador: ");
        String playerName = sc.nextLine();
        System.out.print("Escape Room completado: ");
        String escapeRoom = sc.nextLine();
        System.out.println("✅ Certificado emitido para: " + playerName);
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

