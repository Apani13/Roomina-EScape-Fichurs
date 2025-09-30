package client;

import cat.itacademy.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client client;
    @BeforeEach
    void init() {
        client = new Client("luri", "luri@gmail.com", "698765432", true);
    }
    @Test
    void whenCreatClientWithAcceptedNotifications_thenItIsSavedSuccessfully(){
        assertAll("Creat new client",
                ()->assertEquals("luri", client.getUserName()),
                ()->assertEquals("luri@gmail.com", client.getEmail()),
                ()->assertEquals("698765432", client.getPhone()),
                ()->assertEquals(true, client.isAcceptsNotifications())
        );
    }

}
