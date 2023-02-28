package service.impl;

import exception.InvalidGridException;
import java.util.Arrays;
import service.GridValidator;
import util.ShipDigit;

public class GridValidatorImpl implements GridValidator {
    private static final int PRESENT = ShipDigit.PRESENT.intValue();
    private static final int ABSENT = ShipDigit.ABSENT.intValue();
    private static final int MIN_GRID_HIGH = 5;
    private static final int MIN_GRID_WIDTH = 5;

    @Override
    public void validate(int[][] grid) {
        checkNull(grid);
        checkGridSize(grid);
        checkDigits(grid);
        checkShipsPlacement(grid);
    }

    private void checkNull(int[][] grid) {
        if (grid == null) {
            throw new InvalidGridException("Input grid can`t be null");
        }
    }

    private void checkGridSize(int[][] grid) {
        if (grid[0].length < MIN_GRID_WIDTH || grid.length < MIN_GRID_HIGH) {
            throw new InvalidGridException(
                    String.format("Invalid grid size. Should be at least width='%d', high='%d'",
                            MIN_GRID_WIDTH, MIN_GRID_HIGH));
        }
    }

    private void checkDigits(int[][] grid) {
        Arrays.stream(grid)
                .flatMapToInt(Arrays::stream)
                .filter(digit ->
                        digit != PRESENT
                        && digit != ABSENT)
                .findAny().ifPresent(invalidDigit -> {
                    throw new InvalidGridException(
                            String.format("Only '%d' and '%d' are allowed in grid, but found '%d'",
                                    PRESENT, ABSENT, invalidDigit));
                });
    }

    private void checkShipsPlacement(int[][] grid) {
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] == PRESENT
                        && (grid[i + 1][j] == ABSENT || grid[i][j + 1] == ABSENT) && grid[i + 1][j + 1] == PRESENT
                        || grid[i][j] == PRESENT
                        && (grid[i - 1][j] == ABSENT || grid[i][j + 1] == ABSENT) && grid[i - 1][j + 1] == PRESENT
                        || grid[i][j] == PRESENT
                        && (grid[i + 1][j] == ABSENT || grid[i][j - 1] == ABSENT) && grid[i + 1][j - 1] == PRESENT
                        || grid[i][j] == PRESENT
                        && (grid[i - 1][j] == ABSENT || grid[i][j - 1] == ABSENT) && grid[i - 1][j - 1] == PRESENT) {
                    throw new InvalidGridException("Invalid ship placement. Ships should not touch or overlap");
                }
            }
        }
    }
}
