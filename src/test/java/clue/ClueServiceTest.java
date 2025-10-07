package clue;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.model.Clue;
import cat.itacademy.repository.DatabaseConnection;
import cat.itacademy.service.ClueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import static cat.itacademy.message.success.ClueSuccessMessages.CLUE_CREATED;
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

    private Clue newClue(String name, String theme, String desc) {
        return new Clue(name, theme, desc);
    }

    @Test
    void whenAddingValidClue_thenConfirmationMessageIsShown() throws SQLException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

            clueService.addClue(newClue("Sangre en la pared",
                    "puzzle",
            "parece que las manchas siguen un patrón..."
            ));

        System.setOut(originalOut);

        assertEquals(String.format(CLUE_CREATED, "Sangre en la pared"), outContent.toString().trim());
    }


    @Test
    void whenAddingDuplicateName_thenThrowsDuplicateClueException() throws SQLException {
        clueService.addClue(newClue("Retrato fantasma",
                "susto",
                "chan chan chan..."
        ));

        assertThrows(DuplicateException.class,
                () -> clueService.addClue(newClue("Retrato fantasma"
                ,"eneagramas"
                ,"que será, será..."
                )),
                "Debería lanzar excepción InvalidAttributeException");
    }

    /*@Test
    void whenPriceIsNegative_thenInvalidAttributeExceptionIsThrown() {

        assertThrows(InvalidAttributeException.class,
                () -> clueService.addClue(newClue("pañuelo usado",
                        "prueba de ADN",
                        "Quién es el asesino? :("
                )),
                "Debería lanzar excepción InvalidAttributeException");

    }*/

    @Test
    void whenNameIsBlank_thenInvalidAttributeExceptionIsThrown() {

        assertThrows(InvalidAttributeException.class,
                () -> clueService.addClue(newClue(null,
                                "carta",
                                "aaaa"
                )),
                        "Debería lanzar excepción InvalidAttributeException");


    }

    @Test
    public void whenClueListIsEmpty_thenThrowsEmptyListException() throws SQLException {
        assertThrows(EmptyListException.class, ()-> clueService.getClues());
    }
}
