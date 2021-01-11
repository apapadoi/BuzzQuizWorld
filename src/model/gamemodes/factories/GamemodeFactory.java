package model.gamemodes.factories;

import model.gamemodes.Gamemodable;

public interface GamemodeFactory {
    Gamemodable getRandomGamemode();
    void clearGamemodeData();
}
