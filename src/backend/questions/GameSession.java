package backend.questions;

import java.util.List;

/**
 * This abstract class represents a game session (A single game) with the number of the current round, the gamemode chosen by the player for this game, the current
 * skips available for the player, a list of the players and a list of the rounds.
 * @author Thodwrhs Myridis
 * @version 11.11.2020
 * */
public abstract class GameSession implements DoableGameSession{

    private int currentRoundId;
    private Gamemode CurrentGameMode;
    private int CurrentNumOfSkipsAvailable;
    private List<Player> players;
    private List<Round> rounds;

    /**
     * @see DoableGameSession
     * */
    @Override
    public int getCurrentRoundId(){ return this.currentRoundId;}

    /**
     * @see DoableGameSession
     * */
    @Override
    public Gamemode getCurrentGameMode(){ return this.CurrentGameMode;}

    /**
     * @see DoableGameSession
     * */
    @Override
    public int getCurrentNumOfSkipsAvailable(){ return this.CurrentNumOfSkipsAvailable;}

    /**
     * @see DoableGameSession
     * */
    @Override
    public List<Player> getPlayers(){ return this.players;}

    /**
     * @see DoableGameSession
     * */
    @Override
    public List<Round> getRounds(){ return this.rounds;}


}
