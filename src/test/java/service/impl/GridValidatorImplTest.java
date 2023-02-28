package service.impl;

import exception.InvalidGridException;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.GridValidator;

class GridValidatorImplTest {
    private static GridValidator gridValidator;

    @BeforeAll
    static void beforeAll() {
        gridValidator = new GridValidatorImpl();
    }

    @Test
    public void validate_nullInput_notOk() {
        int[][] input = null;
        Assertions.assertThrows(InvalidGridException.class,
                () -> gridValidator.validate(input),
                String.format("Should throw %s when grid is null\n",
                        InvalidGridException.class));
    }

    @Test
    public void validate_gridContainsInvalidDigit_notOk() {
        int[][] input = new int[][]{
                {1, 1, 0, 0, -3},
                {0, 0, 0, 0, 0},
                {0, 2, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1}
        };
        Assertions.assertThrows(InvalidGridException.class,
                () -> gridValidator.validate(input),
                String.format("Should throw %s when grid contains invalid digits\n",
                        InvalidGridException.class));
    }

    @Test
    public void validate_invalidGridSize_notOk() {
        int[][] input = new int[][]{
                {1, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };
        Assertions.assertThrows(InvalidGridException.class,
                () -> gridValidator.validate(input),
                String.format("Should throw %s when grid width or high is less than 5\n",
                        InvalidGridException.class));
    }

    @Test
    public void validate_invalidShipsPlacement_notOk() {
        Set<int[][]> invalidShipPlacements = new HashSet<>();
        invalidShipPlacements.add(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1}
        });
        invalidShipPlacements.add(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1}
        });
        invalidShipPlacements.add(new int[][]{
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1}
        });
        invalidShipPlacements.forEach(input -> Assertions.assertThrows(
                        InvalidGridException.class, () -> gridValidator.validate(input),
                        String.format("Should throw %s when grid contains invalid ships placements\n",
                                InvalidGridException.class)));
    }
}
