package model.gamemodes;

/**
 * This class represents the gamemode that the player is going to choose and contains the gamemode's description, the number of the gamemode taking it from
 * the Enum "GamemodeId, the skips available for a gamemode and the time available for a gamemode. (Skips and time available depend on the gamemode and they differ)
 * @author Thodwrhs Myridis
 * @version 11.11.2020
 * */
public class Gamemode {
    private String description;
    private GamemodeId Id;
    private int InitialSkipsAvailable;
    private int InitialTimeAvailable;

    /**
     * Soon.
     * @param description the {@code String} that will be showed to the player as the description of the gamemode
     * @param Id the {@code GamemodeId} that it is the number of the gamemode chosen from the enum "GamemodeId"
     * @param InitialSkipsAvailable the {@code int} that contains the possible answers
     * @param InitialTimeAvailable the {@code int} that contains the available time of the player
     * */

    /**
     * This method returns the gamemode's description
     * @return The gamemode's description chosen by the player as {@code String}
     * */
    public String getDescription(){ return this.description;}

    /**
     * This method returns the current gamemode chosen by the player
     * @return The gamemode chosen by the player as enum {@code GamemodeId}
     * */
    public GamemodeId getGamemodeId(){ return this.Id;}

    /**
     * This method returns the skips available for the player (it depends on the gamemode chosen)
     * @return The available skips as {@code int}
     * */
    public int getInitialSkipsAvailable(){ return this.InitialSkipsAvailable;}

    /**
     * This method returns the available time for the player (it depends on the gamemode chosen)
     * @return The available time as {@code int}
     * */
    public int getInitialTimeAvailable(){ return this.InitialTimeAvailable;}

}
