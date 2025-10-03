package notification;

import cat.itacademy.model.Client;
import cat.itacademy.service.NotificationService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NotificationServiceTest {
    @Test
    void shouldPrintMessageWhenClientsAreNotified() {
        NotificationService notificationService = new NotificationService();
        Client client1 = new Client("Alice", "alice@mail.com", "123");
        Client client2 = new Client("Bob");

        notificationService.addObserver(client1);
        notificationService.addObserver(client2);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        notificationService.notifyObservers("Nueva sala disponible!");

        System.setOut(originalOut);

        String output = outContent.toString();
        assertAll(
                ()-> assertTrue(output.contains("📩 Notificación para Alice: Nueva sala disponible!")),
                ()-> assertFalse(output.contains("📩 Notificación para Bob: Nueva sala disponible!"))
        );
    }

}
