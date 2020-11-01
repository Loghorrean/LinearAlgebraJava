package com.loghorrean.algebra;

import java.util.ArrayList;

public class MathFunctions {
    public static int findNOD(int a, int b) {
        while (a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return a + b;
    }

    public static int findNOK(int a, int b) {
        int c = 0;
        while(true) {
            c += a;
            if (c % b == 0) {
                return c;
            }
        }
    }

    public static int getNextPrimeNumber(int a) {
        if (a < 2) {
            return 2;
        }
        while(true) {
            boolean isPrime = true;
            a++;
            for (int i = 2; i < a; i++) {
                if (a % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) {
                continue;
            }
            return a;
        }
    }

    // разложение числа на простые множители (при вводе отрицательного числа, 0 или 1 возвращает null)
    public static ArrayList<Integer> decomposeIntoPrimeNumbers(int a) {
        if (a <= 1) {
            return null;
        }
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        int start = 0;
        while (a != 1) {
            start = getNextPrimeNumber(start);
            if (a % start == 0) {
                a = a / start;
                primeNumbers.add(start);
                start = 0;
            }
        }
        return primeNumbers;
    }

    public static Integer getFactorial(int number) {
        if (number <= 1) {
            return 1;
        }
        int answer = 1;
        for(int i = 2; i <= number; ++i) {
            answer *= i;
        }
        return answer;
    }

    public static Integer getNumberOfCombinations(int k, int n) {
        if (k > n) {
            return 0;
        }
        int answer = 1;
        for (int i = n - k + 1; i <= n; ++i) {
            answer *= i;
        }
        return answer / MathFunctions.getFactorial(k);
    }

    public static Integer getNumberOfPlacements(int k, int n) {
        if (k > n) {
            return 0;
        }
        int answer = 1;
        for (int i = n - k + 1; i <= n; ++i) {
            answer *= i;
        }
        return answer;
    }
}
