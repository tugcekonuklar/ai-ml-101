package com.tkonuklar.logic;

public class TruthTable {


    private static void printTruthTable(int n) {
        int rows = (int) Math.pow(2, n);
        System.out.println("Row Count: " + rows);
        for (int i = 0; i < rows; i++) {
            System.out.print(String.format("Row %s -> ", i));
            for (int j = n - 1; j >= 0; j--) {
                System.out.print((i / (int) Math.pow(2, j)) % 2 + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printTruthTable(4); //enter any natural int
    }

}
