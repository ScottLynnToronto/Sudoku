package com.company;
import java.io.File;

public class SudokuValidator {

    public static int squareLocations[][] =
            {       {0, 0}, {0, 1}, {0, 2},
                    {1, 0}, {1, 1}, {1, 2},
                    {2, 0}, {2, 1}, {2, 2}};

    /**
     * The sudoku is broken up into nine quadrants that are each composed of nine cells each.
     * Each quadrant is composed of three by three cells.
     * This method checks whether every number between 1 and 9 appears once and only once in each quadrant.
     *
     * @param sudoku The Sudoku to inspect
     * @return a boolean variable that represents whether the it passes this test
     */
    private static boolean IsValidSquares(Sudoku s)
    {
        if (s == null)
            return false;
        // Inspect all nine quadrants. The Sudoku is broken up into a 3x3 grid.
        for (int i = 0 ; i < 3; i++)
        {
            for (int j =0; j < 3; j++)
            {
                boolean[] VerticalLine = new boolean[Sudoku.SUDOKU_SIZE];
                for (int k = 0; k < squareLocations.length; k++) {
                    int row = squareLocations[k][0] + i *3 ;
                    int column = squareLocations[k][1] + j*3;
                    int value = s.get(row, column) - 1;
                    // If the number already appears in the box then solution is false
                    if (VerticalLine[value] == true)
                        return false;
                    VerticalLine[value] = true;
                }
            }
        }
        return true;
    }

    /**
     * This method checks whether every number between 1 and 9 appears once and only once in each row.
     *
     * @param sudoku The Sudoku to inspect
     * @return a boolean variable that represents whether the it passes this test
     */
    private static boolean IsValidHorizontalRows(Sudoku sudoku)
    {
        for (int row = 0; row < Sudoku.SUDOKU_SIZE; row++) {
            // By default all values are set to false
            boolean[] line = new boolean[Sudoku.SUDOKU_SIZE];
            for (int column = 0; column < Sudoku.SUDOKU_SIZE; column++) {
                int value = sudoku.get(row, column) - 1;
                // Number already appears in this row. It is a duplicate
                if (line[value] == true)
                    return false;
                line[value] = true;
            }
            // Verify that all the values are true
            if (IsRowOrColumnValid(line) == false)
                return false;
        }
        return true;
    }

    /**
     * This method checks whether every number between 1 and 9 appears once and only once in each column.
     *
     * @param sudoku The Sudoku to inspect
     * @return a boolean variable that represents whether the it passes this test
     */
    private static boolean IsValidVerticalColumns(Sudoku sudoku)
    {
        for (int column = 0; column < Sudoku.SUDOKU_SIZE; column++)
        {
            // By default all values are set to false
            boolean[] VerticalLine = new boolean[Sudoku.SUDOKU_SIZE];
            for (int row = 0; row < Sudoku.SUDOKU_SIZE; row++)
            {
                int value = sudoku.get(row, column) -1;
                // Number already appears in this row. It is a duplicate
                if (VerticalLine[value] == true)
                    return false;
                VerticalLine[value] = true;
            }
            // Verify that all the values are true
            if (IsRowOrColumnValid(VerticalLine) == false)
                return false;
        }
        return true;
    }

    /**
     * Verify that the sudoku is valid solution. It needs to pass three tests:
     * 1) Every number between 1 and 9 appears once and only once in each quadrant.
     * 2) Every number between 1 and 9 appears once and only once in each row.
     * 3) Every number between 1 and 9 appears once and only once in each column.
     *
     * @param file The filename path of the file containing the Sudoku file
     * @return a boolean variable that represents whether the it passes this test
     */
    public static boolean IsValidSolution(String file)
    {
        // Read sudoku file
        Sudoku sudoku = SudokuFileReader.ReadSudokuFile(file);
        if (sudoku == null)
            return false;

        // Verify All Horizontal Rows
        boolean horizontalRowsPass = IsValidHorizontalRows(sudoku);
        if (!horizontalRowsPass)
            return false;

        // Verify All Vertical Columns
        boolean verticalColumnsPass = IsValidVerticalColumns(sudoku);
        if (!verticalColumnsPass)
            return false;

        // Verify Boxes
        if (!IsValidSquares(sudoku))
            return false;

        // Passes all three tests, so the solution is valid
        return true;
    }

    /**
     * This helper function determines if the number 1-9 appears only once.
     * It is a boolean array. For example, if 3 appears then the boolean array would have the third element set to true
     *
     * @param rowOrColumn The boolean array to inspect
     * @return a boolean variable that represents whether the it passes this test
     */
    private static boolean IsRowOrColumnValid(boolean[] rowOrColumn)
    {
        for (int i=0; i<Sudoku.SUDOKU_SIZE; i++)
        {
            if (rowOrColumn[i] == false)
                return false;
        }
        return true;
    }
}
