import cat.itacademy.models.Clue;
import cat.itacademy.exceptions.InvalidPriceException;
import cat.itacademy.exceptions.InvalidTextException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClueTest {

    private static final double EPS = 1e-9; //permite un margen para la comparacion de decimales

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
                () -> assertEquals(3.95, clue.getPrice(), EPS)
        );
    }

    @Test
    void whenPriceIsNaN_thenThrowsInvalidPriceException() {
        assertThrows(InvalidPriceException.class,
                () -> new Clue("Name", "Theme", "Desc", Double.NaN));
    }

    @Test
    void whenPriceIsInfinite_thenThrowsInvalidPriceException() {
        assertThrows(InvalidPriceException.class,
                () -> new Clue("Name", "Theme", "Desc", Double.POSITIVE_INFINITY));
    }

    @Test
    void whenPriceIsNegative_thenThrowsInvalidPriceException() {
        assertThrows(InvalidPriceException.class,
                () -> new Clue("Name", "Theme", "Desc", -0.01));
    }

    @Test
    void whenNameIsBlank_thenThrowsInvalidTextException() {
        assertThrows(InvalidTextException.class,
                () -> new Clue("   ", "Theme", "Desc", 1.00));
    }

    @Test
    void whenPriceHasManyDecimals_thenItIsRoundedToTwoDecimals() {
        Clue clue = new Clue("Name", "Theme", "Desc", 1.235);
        assertEquals(1.24, clue.getPrice(), EPS);
    }
}
