package lab4.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColTest {

    @Test
    void testValidColFrom() {
        assertEquals(Col.Left, Col.from("1"));
        assertEquals(Col.Left, Col.from("l"));
        assertEquals(Col.Middle, Col.from("2"));
        assertEquals(Col.Middle, Col.from("m"));
        assertEquals(Col.Middle, Col.from("c"));
        assertEquals(Col.Right, Col.from("3"));
        assertEquals(Col.Right, Col.from("r"));
    }

    @Test
    void testValidColFromCaseInsensitive() {
        assertEquals(Col.Left, Col.from("L"));
        assertEquals(Col.Middle, Col.from("M"));
        assertEquals(Col.Middle, Col.from("C"));
        assertEquals(Col.Right, Col.from("R"));
    }

    @Test
    void testInvalidColFrom() {
        assertThrows(IllegalArgumentException.class, () -> Col.from("X"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("4"));
        assertThrows(IllegalArgumentException.class, () -> Col.from(""));
        assertThrows(IllegalArgumentException.class, () -> Col.from("middle")); // Not a valid single-character input
    }
}
