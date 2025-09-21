import cat.itacademy.models.Clue;
import cat.itacademy.services.ClueManagement;
import cat.itacademy.exceptions.DuplicateClueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ClueManagementTest {

    private ClueManagement management;

    @BeforeEach
    void setUp() {
        management = new ClueManagement();
    }

    private Clue newValidClue(String name) {
        return new Clue(
                name,
                "puzzle",
                "Seems like the colors follow a pattern...",
                10.45
        );
    }

    @Test
    void whenAddingValidClue_thenConfirmationMessageIsShown() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            Clue clue = new Clue(
                    "Grandma's Portrait",
                    "Photo",
                    "This photo has something written in the back...",
                    3.95
            );
            management.addClue(clue);

        } finally {
            System.setOut(originalOut); // Capturamos System.out y usamos try/finally para restaurarlo SIEMPRE,
                                        // incluso si addClue lanza una excepción. Así evitamos dejar la salida
                                        // estándar redirigida y provocar efectos secundarios en otros tests.
        }

        assertEquals("The clue 'Grandma's Portrait' has been successfully added!",
                outContent.toString().trim());
    }


    @Test
    void whenAddingDuplicateName_thenThrowsDuplicateClueException() {
        management.addClue(newValidClue("Grandma's Portrait"));

        assertThrows(DuplicateClueException.class,
                () -> management.addClue(newValidClue("Grandma's Portrait")),
                "A clue with this name already exists");
    }

}
