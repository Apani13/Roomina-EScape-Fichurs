package item;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Item;
import cat.itacademy.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemServiceTest {

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        this.itemService = new ItemService();
    }

    @Test
    public void whenCreateItemWithInvalidAttributes_thenInvalidItemAttributesExceptionIsThrown() {
        Item objectWithNoName = new Item("", "Plastic", 10);
        Item objectWithNoMaterial = new Item("Candle", "", 10);
        Item objectWithInvalidQuantity = new Item("Candle", "Plastic", -1);

        assertAll("Probando que se lanzan las excepciones correctamente",
            () -> assertThrows(InvalidAttributeException.class,
                    () -> itemService.addItem(objectWithNoName)),
            () -> assertThrows(InvalidAttributeException.class,
                    () -> itemService.addItem(objectWithNoMaterial)),
            () -> assertThrows(InvalidAttributeException.class,
                    () -> itemService.addItem(objectWithInvalidQuantity)));
        }


    @Test
    public void whenCreateItemAlreadyExists_thenThrownDuplicatedException() throws SQLException {
        Item item = new Item("Lámpara", "Plástico", 10);
        Item item1 = new Item("Lámpara", "Plástico", 10);

        itemService.addItem(item);
        assertThrows(DuplicateException.class, () -> itemService.addItem(item1));
    }
}

