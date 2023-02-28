package service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import service.GridValidator;
import service.ShipsCounter;

class ShipsCounterImplTest {
    private static final Map<int[][], Integer> validInputs = new HashMap<>();
    private static ShipsCounter shipsCounter;

    @BeforeAll
    static void beforeAll() {
        GridValidator gridValidator = Mockito.mock(GridValidator.class);
        shipsCounter = new ShipsCounterImpl(gridValidator);
        validInputs.put(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1}}, 6);
        validInputs.put(new int[][]{
                {1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0,},
                {1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1,},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1,},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1,},
                {0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1,},
                {0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1,},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,}}, 14);
        validInputs.put(new int[][]{
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0, 1, 0, 1}}, 11);
        validInputs.put(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 1}}, 9);
    }

    @Test
    public void count_validGridInput_ok() {
        validInputs.forEach((input, expected) -> {
            int actual = shipsCounter.count(input);
            Assertions.assertEquals(expected, actual,
                    String.format("The method should return %s for the input array:\n%s\n",
                            expected, prettyArray(input)));
        });
    }

    private String prettyArray(int[][] array) {
        return Arrays.stream(array)
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
}
