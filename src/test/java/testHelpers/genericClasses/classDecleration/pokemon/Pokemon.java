package testHelpers.genericClasses.classDecleration.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pokemon {
    private String name;
    private int level;
    private double height;
    private boolean isShiny;
}
