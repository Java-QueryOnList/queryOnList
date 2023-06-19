package testHelpers.genericClasses.classTestCases.pokemon;

import testHelpers.genericClasses.classDecleration.pokemon.Pokemon;
import testHelpers.genericClasses.classDecleration.pokemon.PokemonTrainer;
import testHelpers.genericClasses.classObjects.pokemon.PokemonTrainerObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class PokemonTrainerTestCases {
    private static final List<PokemonTrainer> rawList01 = PokemonTrainerObjects.getRawList();
    public static final PreparedCase<PokemonTrainer> filterOnIntArray;
    public static final PreparedCase<PokemonTrainer> orderByOnIntArray;
    public static final PreparedCase<PokemonTrainer> filterOnObjectArray;
    public static final PreparedCase<PokemonTrainer> orderByOnObjectArray;

    static {
        filterOnIntArray = case01();
        orderByOnIntArray = case02();
        filterOnObjectArray = case03();
        orderByOnObjectArray = case04();
    }

    private static PreparedCase<PokemonTrainer> case01() {
        // Create query
        String query = "$filter=badgesIDs ge 7";

        // prepare the List which is expected after the query
        List<PokemonTrainer> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(8));
        expectedList.add(rawList01.get(9));
        expectedList.add(rawList01.get(12));
        expectedList.add(rawList01.get(13));
        expectedList.add(rawList01.get(18));
        expectedList.add(rawList01.get(19));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<PokemonTrainer> case02() {
        // Create query
        String query = "$orderBy=badgesIDs";

        // prepare the List which is expected after the query
        List<PokemonTrainer> expectedList = rawList01.stream()
                .sorted(Comparator.comparingInt(trainer ->
                        Arrays.stream(trainer.getBadgesIDs()).min().orElse(Integer.MIN_VALUE)
                )).collect(Collectors.toList());

        // Prepare getters for order check
        List<Function<PokemonTrainer, ?>> gettersForOrderBy = List.of(PokemonTrainer::getBadgesIDs);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }

    private static PreparedCase<PokemonTrainer> case03() {
        // Create query
        String query = "$filter=pokemonTeam.level gt 50";

        // prepare the List which is expected after the query
        List<PokemonTrainer> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(8));
        expectedList.add(rawList01.get(13));
        expectedList.add(rawList01.get(15));
        expectedList.add(rawList01.get(16));
        expectedList.add(rawList01.get(17));
        expectedList.add(rawList01.get(18));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<PokemonTrainer> case04() {
        // Create query
        String query = "$orderBy=pokemonTeam.level desc";

        // prepare the List which is expected after the query
        List<PokemonTrainer> expectedList = rawList01.stream().sorted(Comparator.comparingInt(trainer ->
                Stream.of(trainer.getPokemonTeam())
                        .mapToInt(Pokemon::getLevel)
                        .max()
                        .orElse(Integer.MAX_VALUE))).collect(Collectors.toList()
        );
        Collections.reverse(expectedList);

        // Prepare getters for order check
        Function<PokemonTrainer, Integer> levelGetter = (trainer) -> {
            Pokemon[] team = trainer.getPokemonTeam();
            if (team.length == 0) {
                return Integer.MAX_VALUE;
            }
            // max because descending
            return Arrays
                    .stream(team)
                    .mapToInt(Pokemon::getLevel)
                    .max()
                    .getAsInt();
        };
        List<Function<PokemonTrainer, ?>> gettersForOrderBy = List.of(levelGetter);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }
}