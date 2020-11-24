package model.gamemodes;

import model.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointBuilderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
        PointBuilder test=new PointBuilder();
        assertEquals("Point Builder",test.toString());
        System.out.println("Get Point Builder");
    }

    @Test
    void actionIfCorrectAnswer() {
        PointBuilder test=new PointBuilder();
        Model model=new Model();
        test.actionIfCorrectAnswer(model,1000);
        assertEquals(1000,model.getScore());
        System.out.println("Get 1000 points");
    }
}