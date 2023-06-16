package testHelpers.genericClasses.classDecleration.pokemon;

import lombok.Data;

@Data
public class PokemonTrainer {
    private int[] badgesIDs;
    private Pokemon[] pokemonTeam;

    public PokemonTrainer() {
        this.badgesIDs = new int[8];
        this.pokemonTeam = new Pokemon[6];
    }

    public PokemonTrainer(int[] badgesIDs, Pokemon[] pokemonTeam) {
        this.badgesIDs = new int[8];
        this.pokemonTeam = new Pokemon[6];

        if (badgesIDs.length <= 8) {
            this.badgesIDs = badgesIDs;
        }

        if (pokemonTeam.length <= 6) {
            this.pokemonTeam = pokemonTeam;
        }
    }
}
