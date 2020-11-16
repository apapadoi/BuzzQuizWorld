package model.gameSessions;

import model.gamemodes.Gamemode;
import model.player.Player;
import model.round.Round;

import java.util.List;

/**
 * This abstract class represents a game session (A single game) with the number of the current round, the gamemode chosen by the player for this game, the current
 * skips available for the player, a list of the players and a list of the rounds.
 * @author Thodwrhs Myridis
 * @version 16.11.2020
 * */
public class GameSession implements DoableGameSession {

    private int currentRoundId;
    private Gamemode CurrentGameMode;
    private int CurrentNumOfSkipsAvailable;
    private List<Player> players;
    private List<Round> rounds;

    public GameSession() {
        this.currentRoundId=0;
        this.CurrentGameMode=null;
        this.CurrentNumOfSkipsAvailable=0;
        this.players=null;
        this.rounds=null;
    }
    public GameSession(int currentRoundId,Gamemode CurrentGameMode,int CurrentNumOfSkipsAvailable,List<Player> players,List<Round> rounds) {
        this.currentRoundId=currentRoundId;
        this.CurrentGameMode=CurrentGameMode;
        this.CurrentNumOfSkipsAvailable=CurrentNumOfSkipsAvailable;
        this.players=players;
        this.rounds=rounds;
    }
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
