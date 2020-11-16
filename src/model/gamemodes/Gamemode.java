package model.gamemodes;

/**
 * This class represents the gamemode that the player is going to choose and contains the gamemode's description, the number of the gamemode taking it from
 * the Enum "GamemodeId, the skips available for a gamemode and the time available for a gamemode. (Skips and time available depend on the gamemode and they differ)
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
public class Gamemode {
    private String description;
    private GamemodeId id;
    private int InitialSkipsAvailable;
    private int InitialTimeAvailable;

    public Gamemode(String idString){
        this.description=" ";
        this.InitialSkipsAvailable=0;
        this.InitialTimeAvailable=0;
        for (GamemodeId gamemode:GamemodeId.values()){
            if (gamemode.toString().equals(idString)){
                id=gamemode;
            }
        }
    }
    /**
     * This method returns the gamemode's description
     * @return The gamemode's description chosen by the player as {@code String}
     * */
    public String getDescription(){ return this.description;}

    /**
     * This method returns the current gamemode chosen by the player
     * @return The gamemode chosen by the player as enum {@code GamemodeId}
     * */
    public GamemodeId getGamemodeId(){ return this.id;}

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
