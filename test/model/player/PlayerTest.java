package model.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void getUsername() {
        Player player = new Player("Test Username 1",250,2);
        assertEquals("Test Username 1",player.getUsername());
    }

    @Test
    void setUsername() {
        Player player = new Player("Test Username 1",250,2);
        player.setUsername("changed username");
        assertEquals("changed username",player.getUsername());
    }

    @Test
    void getScore() {
        Player player = new Player("Test Username 1",250,2);
        assertEquals(250,player.getScore());
    }

    @Test
    void addScore() {
        Player player = new Player("Test Username 1",250,2);
        player.addScore(250);
        assertEquals(500, player.getScore());
        player.addScore(-500);
        assertEquals(0,player.getScore());
    }

    @Test
    void addWins() {
        Player player = new Player("Test Username 1",250,2);
        player.addWins(2);
        assertEquals(4, player.getWins());
        player.addWins(-4);
        assertEquals(0, player.getWins());
    }

    @Test
    void getWins() {
        Player player = new Player("Test Username 1",250,2);
        assertEquals(2, player.getWins());
    }

    @Test
    void setScore() {
        Player player = new Player("Test Username 1",250,2);
        player.setScore(1000);
        assertEquals(1000, player.getScore());
        player.setScore(-1000);
        assertEquals(-1000, player.getScore());
        player.setScore(0);
        assertEquals(0, player.getScore());
    }

    @Test
    void setWins() {
        Player player = new Player("Test Username 1",250,2);
        player.setWins(1000);
        assertEquals(1000, player.getWins());
        player.setWins(0);
        assertEquals(0, player.getWins());
        player.setWins(-1000);
        assertEquals(-1000, player.getWins());
    }
}