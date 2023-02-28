package service.impl;

import service.GridValidator;
import service.ShipsCounter;
import util.ShipDigit;

public class ShipsCounterImpl implements ShipsCounter {
    private final GridValidator gridValidator;

    public ShipsCounterImpl(GridValidator gridValidator) {
        this.gridValidator = gridValidator;
    }

    @Override
    public int count(int[][] grid) {
        gridValidator.validate(grid);
        int shipCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == ShipDigit.PRESENT.intValue()
                        && (i == 0 || grid[i - 1][j] == ShipDigit.ABSENT.intValue())
                        && (j == 0 || grid[i][j - 1] == ShipDigit.ABSENT.intValue())) {
                    shipCount++;
                }
            }
        }
        return shipCount;
    }
}
