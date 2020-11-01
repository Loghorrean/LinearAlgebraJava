package com.loghorrean.algebra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double[][] mainArray = {
                {-1, 1, 4, 1, 0, 0},
                {1, -3, 2, 0, 1, 0},
                {1, 1, 2, 0, 0, 1}
        };
        Matrix mainMatrix = new Matrix(mainArray);
        double[] topRow = {1, -1, 2, 0, 0, 0};
        double[][] array = {
                {4, 0, 0},
                {2, 1, 0},
                {2, 0, 1}
        };
        Matrix matrix = new Matrix(array);
        double[] row = {2, 0, 0};
        double[] col = {-1, 0, 0, 0};
        try {
            double determinant = matrix.getDeterminantRecursively();
            if (determinant != 0) {
                System.out.println("Determinant of this matrix is not equal to zero: \n" + matrix);
                matrix.addRow(row, Direction.TOP);
                matrix.addCol(col, Direction.LEFT);
                System.out.println("Extended matrix is: \n" + matrix);
                double [][] array1 = {
                        {0},
                        {2},
                        {3},
                        {5}
                };
                Matrix newMatrix = new Matrix(array1);
                Matrix inversed = matrix.getInverseMatrix();
                System.out.println("Inverting the given matrix: \n" + inversed);
                System.out.println("A column of values to multiply with the matrix:  \n" + newMatrix);
                Matrix resultSet = inversed.multiplyMatrixes(newMatrix);
                System.out.println("The new given set is: \n" + resultSet);
            }
        } catch (MatrixException e) {
            System.out.println(e.getMessage());
        }
    }
}
