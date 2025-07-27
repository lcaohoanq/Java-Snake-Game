package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameMode {

    CLASSIC("Classic"),
    NO_MAZE("NoMaze"),
    BOX("Box"),
    TUNNEL("Tunnel"),
    MILL("Mill"),
    RAILS("Rails"),
    APARTMENT("Apartment"),
    CAMPAIGN("Campaign");

    private final String displayName;

}
