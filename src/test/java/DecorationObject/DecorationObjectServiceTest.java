package DecorationObject;

import cat.itcademy.exceptions.DuplicateException;
import cat.itcademy.exceptions.InvalidAtributeException;
import cat.itcademy.models.DecorationObject;
import cat.itcademy.services.DecorationObjectService;
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
            () -> assertThrows(InvalidAtributeException.class,
                    () -> decorationObjectService.addDecorationObject(objectWithNoName)),
            () -> assertThrows(InvalidAtributeException.class,
                    () -> decorationObjectService.addDecorationObject(objectWithNoMaterial)),
            () -> assertThrows(InvalidAtributeException.class,
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

