package decorationObject;

import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.DecorationObject;
import cat.itacademy.services.DecorationObjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DecorationObjectServiceTest {

    private DecorationObjectService decorationObjectService;

    @BeforeEach
    void setUp() {
        this.decorationObjectService = new DecorationObjectService();
    }

    @Test
    public void whenCreatedaDecorationObjectWhithInvalidAtributes_thenInvalidDecorationAtributesEsceptionIsThrown() {
        DecorationObject objectWithNoName = new DecorationObject("", "Plastic", 10);
        DecorationObject objectWithNoMaterial = new DecorationObject("Candle", "", 10);
        DecorationObject objectWithInvalidQuantity = new DecorationObject("Candle", "Plastic", -1);

        assertAll("Probando que se lanzan las excepciones correctamente",
            () -> assertThrows(InvalidAttributeException.class,
                    () -> decorationObjectService.addDecorationObject(objectWithNoName)),
            () -> assertThrows(InvalidAttributeException.class,
                    () -> decorationObjectService.addDecorationObject(objectWithNoMaterial)),
            () -> assertThrows(InvalidAttributeException.class,
                    () -> decorationObjectService.addDecorationObject(objectWithInvalidQuantity)));
        }


    @Test
    public void whenCreatedADecorationObjectAlreadyExists_thenThrownDuplicatedException() {
        DecorationObject decorationObject = new DecorationObject("Lámpara", "Plástico", 10);
        DecorationObject decorationObject1 = new DecorationObject("Lámpara", "Plástico", 10);

        decorationObjectService.addDecorationObject(decorationObject);
        assertThrows(DuplicateException.class, () -> decorationObjectService.addDecorationObject(decorationObject1));
    }
}

