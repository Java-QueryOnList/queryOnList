package testHelpers.genericClasses.classObjects.pokemon;

import testHelpers.genericClasses.classDecleration.pokemon.Pokemon;
import testHelpers.genericClasses.classDecleration.pokemon.PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class PokemonTrainerObjects {
    private static final List<PokemonTrainer> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<PokemonTrainer> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        List<Pokemon> availablePokemon = PokemonObjects.getRawList();
        RAW_LIST.add(new PokemonTrainer(new int[]{1, 2}, new Pokemon[]{availablePokemon.get(2), availablePokemon.get(3)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{1, 2, 3}, new Pokemon[]{availablePokemon.get(4), availablePokemon.get(5)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{4, 5}, new Pokemon[]{availablePokemon.get(0), availablePokemon.get(1)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{6, 7, 8}, new Pokemon[]{availablePokemon.get(6), availablePokemon.get(7)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{1, 3, 5, 7}, new Pokemon[]{availablePokemon.get(8), availablePokemon.get(9)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{2, 4, 6}, new Pokemon[]{availablePokemon.get(10), availablePokemon.get(11)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{}, new Pokemon[]{availablePokemon.get(12), availablePokemon.get(13)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{2, 5, 6}, new Pokemon[]{availablePokemon.get(14), availablePokemon.get(15)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{1, 4, 7}, new Pokemon[]{availablePokemon.get(16), availablePokemon.get(17)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{3, 6, 8}, new Pokemon[]{availablePokemon.get(18), availablePokemon.get(19)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{1, 2}, new Pokemon[]{availablePokemon.get(0), availablePokemon.get(19)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{3, 4, 5}, new Pokemon[]{availablePokemon.get(2), availablePokemon.get(18)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{6, 7}, new Pokemon[]{availablePokemon.get(3), availablePokemon.get(17)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{8}, new Pokemon[]{availablePokemon.get(4), availablePokemon.get(16)}));
        RAW_LIST.add(new PokemonTrainer());
        RAW_LIST.add(new PokemonTrainer(new int[]{2, 4, 6}, new Pokemon[]{availablePokemon.get(5), availablePokemon.get(15)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{}, new Pokemon[]{availablePokemon.get(6), availablePokemon.get(13)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{2, 5, 6}, new Pokemon[]{availablePokemon.get(7), availablePokemon.get(12)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{1, 4, 7}, new Pokemon[]{availablePokemon.get(8), availablePokemon.get(11)}));
        RAW_LIST.add(new PokemonTrainer(new int[]{3, 6, 8}, new Pokemon[]{availablePokemon.get(9), availablePokemon.get(10)}));
    }
}

/*
rawList01:

PokemonTrainer(badgesIDs=[1, 2],                    pokemonTeam=[Pokemon(name=Charizard, level=36, height=1.7, isShiny=false), Pokemon(name=Squirtle, level=5, height=0.5, isShiny=false)])
PokemonTrainer(badgesIDs=[1, 2, 3],                 pokemonTeam=[Pokemon(name=Jigglypuff, level=12, height=0.5, isShiny=false), Pokemon(name=Mewtwo, level=70, height=2.0, isShiny=false)])
PokemonTrainer(badgesIDs=[4, 5],                    pokemonTeam=[Pokemon(name=Pikachu, level=10, height=0.4, isShiny=false), Pokemon(name=Bulbasaur, level=8, height=0.7, isShiny=false)])
PokemonTrainer(badgesIDs=[6, 7, 8],                 pokemonTeam=[Pokemon(name=Eevee, level=15, height=0.3, isShiny=false), Pokemon(name=Machamp, level=42, height=1.6, isShiny=false)])
PokemonTrainer(badgesIDs=[1, 3, 5, 7],              pokemonTeam=[Pokemon(name=Gyarados, level=55, height=6.5, isShiny=false), Pokemon(name=Snorlax, level=30, height=2.1, isShiny=false)])
PokemonTrainer(badgesIDs=[2, 4, 6],                 pokemonTeam=[Pokemon(name=Articuno, level=50, height=1.7, isShiny=false), Pokemon(name=Zapdos, level=55, height=1.6, isShiny=false)])
PokemonTrainer(badgesIDs=[],                        pokemonTeam=[Pokemon(name=Moltres, level=60, height=2.0, isShiny=false), Pokemon(name=Dragonite, level=62, height=2.2, isShiny=false)])
PokemonTrainer(badgesIDs=[2, 5, 6],                 pokemonTeam=[Pokemon(name=Mew, level=40, height=0.4, isShiny=false), Pokemon(name=Gengar, level=45, height=1.5, isShiny=true)])
PokemonTrainer(badgesIDs=[1, 4, 7],                 pokemonTeam=[Pokemon(name=Alakazam, level=55, height=1.3, isShiny=false), Pokemon(name=Lapras, level=25, height=2.5, isShiny=false)])
PokemonTrainer(badgesIDs=[3, 6, 8],                 pokemonTeam=[Pokemon(name=Vaporeon, level=30, height=1.0, isShiny=false), Pokemon(name=Jolteon, level=28, height=0.8, isShiny=false)])
PokemonTrainer(badgesIDs=[1, 2],                    pokemonTeam=[Pokemon(name=Pikachu, level=10, height=0.4, isShiny=false), Pokemon(name=Jolteon, level=28, height=0.8, isShiny=false)])
PokemonTrainer(badgesIDs=[3, 4, 5],                 pokemonTeam=[Pokemon(name=Charizard, level=36, height=1.7, isShiny=false), Pokemon(name=Vaporeon, level=30, height=1.0, isShiny=false)])
PokemonTrainer(badgesIDs=[6, 7],                    pokemonTeam=[Pokemon(name=Squirtle, level=5, height=0.5, isShiny=false), Pokemon(name=Lapras, level=25, height=2.5, isShiny=false)])
PokemonTrainer(badgesIDs=[8],                       pokemonTeam=[Pokemon(name=Jigglypuff, level=12, height=0.5, isShiny=false), Pokemon(name=Alakazam, level=55, height=1.3, isShiny=false)])
PokemonTrainer(badgesIDs=[0, 0, 0, 0, 0, 0, 0, 0],  pokemonTeam=[null, null, null, null, null, null])
PokemonTrainer(badgesIDs=[2, 4, 6],                 pokemonTeam=[Pokemon(name=Mewtwo, level=70, height=2.0, isShiny=false), Pokemon(name=Gengar, level=45, height=1.5, isShiny=true)])
PokemonTrainer(badgesIDs=[],                        pokemonTeam=[Pokemon(name=Eevee, level=15, height=0.3, isShiny=false), Pokemon(name=Dragonite, level=62, height=2.2, isShiny=false)])
PokemonTrainer(badgesIDs=[2, 5, 6],                 pokemonTeam=[Pokemon(name=Machamp, level=42, height=1.6, isShiny=false), Pokemon(name=Moltres, level=60, height=2.0, isShiny=false)])
PokemonTrainer(badgesIDs=[1, 4, 7],                 pokemonTeam=[Pokemon(name=Gyarados, level=55, height=6.5, isShiny=false), Pokemon(name=Zapdos, level=55, height=1.6, isShiny=false)])
PokemonTrainer(badgesIDs=[3, 6, 8],                 pokemonTeam=[Pokemon(name=Snorlax, level=30, height=2.1, isShiny=false), Pokemon(name=Articuno, level=50, height=1.7, isShiny=false)])
*/