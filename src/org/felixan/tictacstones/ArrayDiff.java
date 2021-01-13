package org.felixan.tictacstones;

public class ArrayDiff {
    public static boolean checkArrayConstantDiff(int[] inArray) {
        int diff = inArray[1] - inArray[0];
        for (int c = 0; c < inArray.length - 1; c++)
        {
            if (inArray[c + 1] - inArray[c] != diff) {
                return false;
            }
        }
        return true;
    }
}
