package model.questions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testToString1() {
        Category category = Category.Food;
        assertEquals("Food",category.toString());
        category = Category.Technology;
        assertEquals("Technology",category.toString());
        category = Category.Films;
        assertEquals("Films",category.toString());
    }

    @Test
    void testToString2() {
        Category category = Category.Science;
        assertEquals("Science",category.toString());
        category = Category.HipHopArtists;
        assertEquals("Hip Hop Artists",category.toString());
        category = Category.HipHopGroups;
        assertEquals("Hip Hop Groups",category.toString());
    }

    @Test
    void testToString3() {
        Category category = Category.Cars;
        assertEquals("Cars",category.toString());
        category = Category.Formula1;
        assertEquals("Formula 1",category.toString());
    }

    @Test
    void testValueOf1() {
        Category category = Category.valueOf("Food");
        assertEquals(Category.Food, category);
        category = Category.valueOf("Technology");
        assertEquals(Category.Technology, category);
        category = Category.valueOf("Films");
        assertEquals(Category.Films, category);
    }

    @Test
    void testValueOf2() {
        Category category = Category.valueOf("Science");
        assertEquals(Category.Science, category);
        category = Category.valueOf("HipHopArtists");
        assertEquals(Category.HipHopArtists, category);
        category = Category.valueOf("HipHopGroups");
        assertEquals(Category.HipHopGroups, category);
    }

    @Test
    void testValueOf3() {
        Category category = Category.valueOf("Cars");
        assertEquals(Category.Cars, category);
        category = Category.valueOf("Formula1");
        assertEquals(Category.Formula1, category);
    }
}