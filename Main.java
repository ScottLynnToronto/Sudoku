package com.company;

public class Main {

    /**
     * Starting point of the application
     */
    public static void main(String[] args)
    {
        String file = args[0];
        boolean isValid = SudokuValidator.IsValidSolution(file);
        System.out.println(isValid);
    }
}
