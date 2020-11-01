package com.loghorrean.algebra;

import java.util.ArrayList;
import java.util.Scanner;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter the numerator of the fraction: ");
            this.numerator = Integer.parseInt(in.nextLine());
            System.out.println("Enter the denominator of the fration: ");
            this.denominator = Integer.parseInt(in.nextLine());
            if (this.denominator == 0) {
                throw new FractionException("Denominator should not be equal to zero");
            }
            if (this.denominator < 0) {
                this.denominator *= -1;
                this.numerator *= -1;
            }
            this.optimizeFraction();
        } catch (FractionException e) {
            System.out.println(e.getMessage());
        }
    }

    public Fraction(int numerator, int denominator) {
        try {
            if (denominator == 0) {
                throw new FractionException("Denominator cannot be equal to zero");
            }
            if (denominator < 0) {
                denominator *= -1;
                numerator *= -1;
            }
            this.numerator = numerator;
            this.denominator = denominator;
            this.optimizeFraction();
        } catch (FractionException e) {
            System.out.println(e.getMessage());
        }
    }

    public Fraction(Fraction a) {
        this(a.numerator, a.denominator);
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    // сокращает дробь
    private void optimizeFraction() {
        int tempNumerator = this.numerator;
        if (tempNumerator < 0) {
            tempNumerator *= -1;
        }
        int optimizer = MathFunctions.findNOD(tempNumerator, this.denominator);
        this.numerator /= optimizer;
        this.denominator /= optimizer;
    }

    // сложение дроби и дроби
    public Fraction add(Fraction a) {
        int nok = MathFunctions.findNOK(this.denominator, a.denominator);
        int firstMult = nok / this.denominator;
        int secondMult = nok / a.denominator;
        return new Fraction(this.numerator * firstMult + a.numerator * secondMult, this.denominator * firstMult);
    }

    // сложение дроби и числа
    public Fraction add(int a) {
        Fraction temp = new Fraction(a, 1);
        return this.add(temp);
    }

    // вычитание дроби из дроби
    public Fraction subtract(Fraction a) {
        a.numerator *= -1;
        return this.add(a);
    }

    //вычитание числа из дроби
    public Fraction subtract(int a) {
        Fraction temp = new Fraction(-a, 1);
        return this.add(temp);
    }

    // умножение дроби на дробь
    public Fraction multiply(Fraction a) {
        Fraction fraction = new Fraction(this.numerator * a.numerator, this.denominator * a.denominator);
        fraction.optimizeFraction();
        return fraction;
    }

    // умножение дроби на число
    public Fraction multiply(int a) {
        return new Fraction(this.numerator * a, this.denominator);
    }

    // деление дроби на дробь
    public Fraction divide(Fraction a) {
        Fraction fraction = new Fraction(a.denominator, a.numerator);
        return this.multiply(fraction);
    }

    // деление дроби на число
    public Fraction divide(int a) {
        return this.multiply(new Fraction(1, a));
    }

    // возведение дроби в степень
    public Fraction pow(int power) {
        return new Fraction((int) Math.pow(this.numerator, power), (int) Math.pow(this.denominator, power));
    }

    // получение записи неправильной дроби
    public void getImproper() {
        int number = this.numerator / this.denominator;
        String improper = "" + number;
        int remains = this.numerator - number * this.denominator;
        if (remains != 0) {
            improper += " and " + remains + "/" + this.denominator;
        }
        System.out.println(improper);
    }

    // сокращает дробь, возвращая ее десятичное значение
    public double getValueOfFraction() {
        return (double) this.numerator / (double) this.denominator;
    }
}