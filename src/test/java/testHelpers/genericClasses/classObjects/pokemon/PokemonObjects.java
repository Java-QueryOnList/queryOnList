package testHelpers.genericClasses.classObjects.pokemon;

import testHelpers.genericClasses.classDecleration.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonObjects {
    private static final List<Pokemon> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Pokemon> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        RAW_LIST.add(new Pokemon("Pikachu", 10, 0.4, false));
        RAW_LIST.add(new Pokemon("Bulbasaur", 8, 0.7, false));
        RAW_LIST.add(new Pokemon("Charizard", 36, 1.7, false));
        RAW_LIST.add(new Pokemon("Squirtle", 5, 0.5, false));
        RAW_LIST.add(new Pokemon("Jigglypuff", 12, 0.5, false));
        RAW_LIST.add(new Pokemon("Mewtwo", 70, 2.0, false));
        RAW_LIST.add(new Pokemon("Eevee", 15, 0.3, false));
        RAW_LIST.add(new Pokemon("Machamp", 42, 1.6, false));
        RAW_LIST.add(new Pokemon("Gyarados", 55, 6.5, false));
        RAW_LIST.add(new Pokemon("Snorlax", 30, 2.1, false));
        RAW_LIST.add(new Pokemon("Articuno", 50, 1.7, false));
        RAW_LIST.add(new Pokemon("Zapdos", 55, 1.6, false));
        RAW_LIST.add(new Pokemon("Moltres", 60, 2.0, false));
        RAW_LIST.add(new Pokemon("Dragonite", 62, 2.2, false));
        RAW_LIST.add(new Pokemon("Mew", 40, 0.4, false));
        RAW_LIST.add(new Pokemon("Gengar", 45, 1.5, true));
        RAW_LIST.add(new Pokemon("Alakazam", 55, 1.3, false));
        RAW_LIST.add(new Pokemon("Lapras", 25, 2.5, false));
        RAW_LIST.add(new Pokemon("Vaporeon", 30, 1.0, false));
        RAW_LIST.add(new Pokemon("Jolteon", 28, 0.8, false));
    }
}