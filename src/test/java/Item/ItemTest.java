package Item;

import cat.itacademy.models.Item;
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
    public void createDecorationObject_Succes() {
        assertAll("Probando se crea un objecto de decoración con los atributos correctos",
                () -> Assertions.assertEquals("Candle", item.getName()),
                () -> Assertions.assertEquals("Plastic", item.getMaterial()),
                () -> Assertions.assertEquals(10, item.getQuantity()));
    }

}
