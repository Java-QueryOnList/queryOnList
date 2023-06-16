package testHelpers.genericClasses.classTestCases.pokemon;

import testHelpers.genericClasses.classDecleration.pokemon.PokemonTrainer;
import testHelpers.genericClasses.classObjects.pokemon.PokemonTrainerObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.*;
import java.util.stream.Collectors;

public final class PokemonTrainerTestCases {
    private static final List<PokemonTrainer> rawList01 = PokemonTrainerObjects.getRawList();
    public static final PreparedCase<PokemonTrainer> filterOperatorOnIntArray;
    public static final PreparedCase<PokemonTrainer> sortingOperatorOnIntArray;

    static {
        filterOperatorOnIntArray = case01();
        sortingOperatorOnIntArray = case02();
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

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, true);
    }
}