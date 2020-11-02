package com.loghorrean.algebra;

//TODO: implement the generics and custom parameters such as length and indexes
public class ArrayCopier {
    public static double[][] copySecondDimension(double[][] array1) {
        double[][] copyArray = new double[array1[0].length][array1.length];
        for (int i = 0; i < array1.length; ++i) {
            copyArray[i] = ArrayCopier.copyFirstDimension(array1[i]);
        }
        return copyArray;
    }

    public static double[] copyFirstDimension(double[] array1) {
        double[] copyOfArray = new double[array1.length];
        System.arraycopy(array1, 0, copyOfArray, 0, array1.length);
        return copyOfArray;
    }
}
