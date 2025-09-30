package clue;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Clue;
import cat.itacademy.service.ClueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static cat.itacademy.message.success.ClueSuccessMessages.CLUE_CREATED;
import static org.junit.jupiter.api.Assertions.*;

public class ClueServiceTest {

    private ClueService management;

    @BeforeEach
    void setUp() {
        management = new ClueService();
    }

    private Clue newClue(String name, String theme, String desc, double price) {
        return new Clue(name, theme, desc, price);
    }

    @Test
    void whenAddingValidClue_thenConfirmationMessageIsShown() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

            management.addClue(newClue("Sangre en la pared",
                    "puzzle",
            "parece que las manchas siguen un patrón...",
            10.95));

        assertEquals(CLUE_CREATED, outContent.toString().trim());
    }


    @Test
    void whenAddingDuplicateName_thenThrowsDuplicateClueException() {
        management.addClue(newClue("Retrato fantasma",
                "susto",
                "chan chan chan...",
                5.33));

        assertThrows(DuplicateException.class,
                () -> management.addClue(newClue("Retrato fantasma"
                ,"eneagramas"
                ,"que será, será...",
                100.34)),
                "Debería lanzar excepción InvalidAttributeException");
    }

    @Test
    void whenPriceIsNegative_thenInvalidAttributeExceptionIsThrown() {

        assertThrows(InvalidAttributeException.class,
                () -> management.addClue(newClue("pañuelo usado",
                        "prueba de ADN",
                        "Quién es el asesino? :(",
                        -55.22)),
                "Debería lanzar excepción InvalidAttributeException");

    }

    @Test
    void whenNameIsBlank_thenInvalidAttributeExceptionIsThrown() {

        assertThrows(InvalidAttributeException.class,
                () -> management.addClue(newClue(null,
                                "carta",
                                "aaaa",
                                1.55)),
                        "Debería lanzar excepción InvalidAttributeException");


    }

    @Test
    public void whenClueListIsEmpty_thenThrowsEmptyListException() throws SQLException {
        assertThrows(EmptyListException.class, ()->management.getClues());
    }
}
