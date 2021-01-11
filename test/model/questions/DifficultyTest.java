package model.questions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifficultyTest {

    @Test
    void testToString() {
        Difficulty difficulty = Difficulty.Easy;
        assertEquals("Easy", difficulty.toString());
        difficulty = Difficulty.Medium;
        assertEquals("Medium", difficulty.toString());
        difficulty = Difficulty.Hard;
        assertEquals("Hard", difficulty.toString());
    }

    @Test
    void valueOf() {
        Difficulty difficulty = Difficulty.valueOf("Easy");
        assertEquals(Difficulty.Easy, difficulty);
        difficulty = Difficulty.valueOf("Medium");
        assertEquals(Difficulty.Medium, difficulty);
        difficulty = Difficulty.valueOf("Hard");
        assertEquals(Difficulty.Hard, difficulty);
    }
}