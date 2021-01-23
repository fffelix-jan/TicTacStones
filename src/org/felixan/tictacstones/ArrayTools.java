package org.felixan.tictacstones;

import java.util.Arrays;

/**
 * Miscellaneous checks for arrays.
 */
public class ArrayTools {
    /**
     * Checks if a particular array of integers is in an array of arrays of integers.
     * @param array The array to look for.
     * @param arrayOfArrays The array of arrays of integers to look in.
     * @return true if found and false if not.
     */
    public static boolean checkIfArrayInArrayOfArrays(int[] array, int[][] arrayOfArrays) {
        for (int[] curElem : arrayOfArrays) {
            if (Arrays.equals(curElem, array)) {
                return true;
            }
        }
        return false;
    }
}
