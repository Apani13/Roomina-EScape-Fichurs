import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Clue;
import cat.itacademy.services.ClueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ClueManagementTest {

    private ClueService management;
    private static final double EPS = 1e-9;

    @BeforeEach
    void setUp() {
        management = new ClueService();
    }

    private Clue newValidClue(String name) {
        return new Clue(
                name,
                "puzzle",
                "Parece que las manchas siguen un patrón...",
                10.45
        );
    }

    @Test
    void whenAddingValidClue_thenConfirmationMessageIsShown() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

            management.addClue(newValidClue("Sangre en la pared"));

        assertEquals("La pista 'Sangre en la pared' se ha añadido correctamente!",
                outContent.toString().trim());
    }


    @Test
    void whenAddingDuplicateName_thenThrowsDuplicateClueException() {
        management.addClue(newValidClue("Sangre en la pared"));

        assertThrows(DuplicateException.class,
                () -> management.addClue(newValidClue("Sangre en la pared")),
                "Ya existe una pista con este nombre...");
    }

    @Test
    void whenPriceIsNegative_thenInvalidAttributeExceptionIsThrown() {

    }

    @Test
    void whenNameIsBlank_thenInvalidAttributeExceptionIsThrown() {
        assertThrows(InvalidAttributeException.class,
                () -> new Clue("   ", "carta", "Lorem ipsum...", 1.55),
                "The clue is null or empty");


    }



}
