package clue;
import cat.itacademy.exceptions.DuplicateException;
import cat.itacademy.exceptions.InvalidAttributeException;
import cat.itacademy.models.Clue;
import cat.itacademy.repositories.DatabaseConnection;
import cat.itacademy.services.ClueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static cat.itacademy.utils.ClueSuccessMessages.CLUE_CREATED;
import static org.junit.jupiter.api.Assertions.*;

public class ClueServiceTest {

    private ClueService clueService;

    @BeforeEach
    void setUp() {
        clueService = new ClueService();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM clue")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Clue newClue(String name, String theme, String desc, double price) {
        return new Clue(name, theme, desc, price);
    }

    @Test
    void whenAddingValidClue_thenConfirmationMessageIsShown() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

            clueService.addClue(newClue("Sangre en la pared",
                    "puzzle",
            "parece que las manchas siguen un patrón...",
            10.95));

        System.setOut(originalOut);

        assertEquals(String.format(CLUE_CREATED, "Sangre en la pared"), outContent.toString().trim());
    }


    @Test
    void whenAddingDuplicateName_thenThrowsDuplicateClueException() {
        clueService.addClue(newClue("Retrato fantasma",
                "susto",
                "chan chan chan...",
                5.33));

        assertThrows(DuplicateException.class,
                () -> clueService.addClue(newClue("Retrato fantasma"
                ,"eneagramas"
                ,"que será, será...",
                100.34)),
                "Debería lanzar excepción InvalidAttributeException");
    }

    @Test
    void whenPriceIsNegative_thenInvalidAttributeExceptionIsThrown() {

        assertThrows(InvalidAttributeException.class,
                () -> clueService.addClue(newClue("pañuelo usado",
                        "prueba de ADN",
                        "Quién es el asesino? :(",
                        -55.22)),
                "Debería lanzar excepción InvalidAttributeException");

    }

    @Test
    void whenNameIsBlank_thenInvalidAttributeExceptionIsThrown() {

        assertThrows(InvalidAttributeException.class,
                () -> clueService.addClue(newClue(null,
                                "carta",
                                "aaaa",
                                1.55)),
                        "Debería lanzar excepción InvalidAttributeException");


    }



}
