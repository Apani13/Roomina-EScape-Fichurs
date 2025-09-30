package Item;

import cat.itacademy.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ItemTest {

    private Item item;

    @BeforeEach
    void init() {
        item = new Item("Candle", "Plastic", 10);
    }

    @Test
    public void createItem_Success() {
        assertAll("Probando se crea un objeto de decoración con los atributos correctos",
                () -> Assertions.assertEquals("Candle", item.getName()),
                () -> Assertions.assertEquals("Plastic", item.getMaterial()),
                () -> Assertions.assertEquals(10, item.getQuantity()));
    }

}
