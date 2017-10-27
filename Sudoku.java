package com.company;

import java.util.Arrays;

public class Sudoku {
    final static int SUDOKU_SIZE = 9;
    private static int[][] sudokuData = new int[9][9];

    /**
     * Print the Sudoku to console output
     *
     */
    public void printSudoku()
    {
        for (int i =0; i< SUDOKU_SIZE; i++)
        {
            System.out.println(Arrays.toString(sudokuData[i]));
        }
    }

    /**
     * Set the value in a specific cell
     *
     * @param row The row of the cell
     * @param column The column of the cell
     * @return a boolean variable that represents whether or not it was successful
     */
    public boolean set(int row, int column, int value)
    {
        if (row < 0 || row >= SUDOKU_SIZE)
            return false;
        if (column < 0 || column >= SUDOKU_SIZE)
            return false;
        sudokuData[row][column] = value;
        return true;
    }

    /**
     * Get the value in a specific cell
     *
     * @param row The row of the cell
     * @param column The column of the cell
     * @return a boolean variable that represents whether or not it was successful
     */
    public int get(int row, int column)
    {
        if (row < 0 || row >= SUDOKU_SIZE)
            return -1;
        if (column < 0 || column >= SUDOKU_SIZE)
            return -1;
        return sudokuData[row][column];
    }
}
