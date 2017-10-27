package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SudokuFileReader {
    final static int SUDOKU_SIZE = 9;
    static int[][] sudokuData = new int[9][9];

    /**
     * Read the contents of a file containing Sudoku data into an instance of a Sudoku model
     *
     * @param file The filename of the Sudoku file
     * @return sudoku The sudoku model
     */
    public static Sudoku ReadSudokuFile(String file)
    {
        Sudoku s = null;
        // Read the file
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            List<String> list = new ArrayList<>();
            list = stream.collect(Collectors.toList());

            // Remove all empty string elements
            Predicate<String> stringPredicate = item -> item.length() ==0;
            list.removeIf(stringPredicate);

            // The size of the list should be exactly the dimensions of the Sudoku which is by default 9.
            if (list.size() != SUDOKU_SIZE)
                return null;

            int rowIndex = 0;
            s = new Sudoku();

            // Loop through the list and populate the Sudoku cell by cell
            for(String row : list)
            {
                if (row.length() != SUDOKU_SIZE)
                    return null;
                for (int columnIndex=0; columnIndex<SUDOKU_SIZE; columnIndex++) {
                    // If the element is not a numeric value then return null
                    if (row.charAt(columnIndex) < 49 || row.charAt(columnIndex) > 57)
                        return null;
                    // Convert from ascii to the numeric value
                    int number = row.charAt(columnIndex) - 48;
                    s.set(rowIndex, columnIndex, number);
                }
                rowIndex = rowIndex + 1;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return s;
    }
}
