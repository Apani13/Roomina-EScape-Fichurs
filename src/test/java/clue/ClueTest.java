package clue;
import cat.itacademy.model.Clue;
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
                "This photo has something written in the back..."
        );

        assertAll("clue created successfully",
                () -> assertEquals("Grandma's Portrait", clue.getName()),
                () -> assertEquals("Photo", clue.getTheme()),
                () -> assertEquals("This photo has something written in the back...", clue.getDescription()),
                () -> assertEquals(10.0, clue.getPrice())
        );
    }


}
