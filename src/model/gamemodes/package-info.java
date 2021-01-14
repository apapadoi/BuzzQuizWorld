/**
 * This package provides several classes associated with the gamemodes hierarchy .
 * If a new gamemode needs to be added it must implement the interface {@code Gamemodable}.
 * An abstract class {@code Gamemode} is provided with several default implementations for methods of this interface.
 * Also the {@code NumerablePlayersGamemode} interface is provided for separating gamemodes depending the number of
 * players that can play these gamemodes. If new gamemodes needs to be added for e.g. 4 players, a new class need to
 * be added implementing {@code NumerablePlayersGamemode} interface and updating the method {@code}
 * chooseGamemodesForCurrentNumOfPlayers of {@code Model} class is also needed.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 13.1.2021
 */
package model.gamemodes;