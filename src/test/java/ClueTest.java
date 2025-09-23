import cat.itacademy.models.Clue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueTest {

    private Clue clue;

    @Test
    void whenCreatingClueWithValidData_thenObjectHasExpectedFields() {
        clue = new Clue(
                "Grandma's Portrait",
                "Photo",
                "This photo has something written in the back...",
                3.95
        );

        assertAll("clue created successfully",
                () -> assertEquals("Grandma's Portrait", clue.getName()),
                () -> assertEquals("Photo", clue.getTheme()),
                () -> assertEquals("This photo has something written in the back...", clue.getDescription()),
                () -> assertEquals(3.95, clue.getPrice())
        );
    }

}
