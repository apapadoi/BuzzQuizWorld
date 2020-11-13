package model.gameSessions;

import model.gamemodes.Gamemode;
import model.player.Player;
import model.round.Round;

import java.util.List;

/**
 This interface contains the methods that must be implemented by the GameSession class so it can be added as
 a type of a doable game session.
 * @author Thodwrhs Myridis
 * @version 11.11.2020
 * */
public interface DoableGameSession {
    /**
     * This method returns the current round number
     * @return The current round as {@code int}
     * */
    int getCurrentRoundId();

    /**
     * This method returns the current gamemode chosen by the player
     * @return The gamemode chosen by the player as {@code Gamemode}
     * */
    Gamemode getCurrentGameMode();

    /**
     * This method returns the number of available skips left to the player
     * @return The number of skips available for the player as {@code int}
     * */
    int getCurrentNumOfSkipsAvailable();

    /**
     * This method returns a list of the players that have "signed in" and played the game
     * @return All the players that played as {@code List<Player>}
     * */
    List<Player> getPlayers();

    /**
     * This method returns the number of rounds
     * @return The number of rounds as {@code List<Round>}
     * */
    List<Round> getRounds();


}
