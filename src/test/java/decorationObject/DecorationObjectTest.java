package decorationObject;

import cat.itacademy.models.DecorationObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class DecorationObjectTest {

    private DecorationObject decorationObject;

    @BeforeEach
    void init() {
        decorationObject = new DecorationObject("Candle", "Plastic", 10);
    }

    @Test
    public void createDecorationObject_Succes() {
        assertAll("Probando se crea un objecto de decoración con los atributos correctos",
                () -> Assertions.assertEquals("Candle", decorationObject.getName()),
                () -> Assertions.assertEquals("Plastic", decorationObject.getMaterial()),
                () -> Assertions.assertEquals(10, decorationObject.getQuantity()));
    }

}
