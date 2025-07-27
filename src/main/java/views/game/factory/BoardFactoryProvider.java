package views.game.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Provider class that returns the appropriate BoardFactory based on the game mode.
 * This follows the Factory Method pattern to create the right factory.
 */
@Slf4j
public class BoardFactoryProvider {
    
    /**
     * Returns the appropriate BoardFactory based on the game mode.
     * 
     * @param mode The game mode
     * @return The corresponding BoardFactory implementation
     */
    public static BoardFactory getFactory(String mode) {
        log.info("Creating factory for mode: {}", mode);
        
        if (mode == null) {
            throw new IllegalArgumentException("Game mode cannot be null");
        }

        return switch (mode) {
            case "Classic", "NoMaze" -> new NoMazeFactory();
            case "Box" -> new BoxFactory();
            case "Tunnel" -> new TunnelFactory();
            case "Mill" -> new MillFactory();
            case "Rails" -> new RailsFactory();
            case "Apartment" -> new ApartmentFactory();
            case "Campaign" -> new CampaignFactory();
            default -> {
                log.warn("Unknown game mode: {}, defaulting to NoMaze", mode);
                yield new NoMazeFactory();
            }
        };
    }

}