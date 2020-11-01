package com.loghorrean.algebra;

public class Point implements Comparable <Point> {
    protected Double x;
    protected Double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Point increaseX(double x) {
        return new Point(this.x + x, this.y);
    }

    public Point increaseY(double y) {
        return new Point(this.x, this.y + y);
    }

    public String findPointsLocation() {
        String location = "Point lies ";
        if (this.x == 0) {
            if (this.y == 0) {
                location += "in the middle";
            }
            else {
                location += "on axis Y";
            }
        }
        else {
            if (this.y == 0) {
                location += "on axis X";
            }
            else {
                if (this.x > 0) {
                    if (this.y > 0) {
                        location += "in first quarter";
                    }
                    else {
                        location += "in fourth quarter";
                    }
                }
                else {
                    if (this.y > 0) {
                        location += "in second quarter";
                    }
                    else {
                        location += "in third quarter";
                    }
                }
            }
        }
        return location;
    }

    @Override
    public String toString() {
        return "Point: x = " + x + ", y = " + y;
    }

    public int compareTo(Point point) {
        int result = this.x.compareTo(point.x);
        if (result == 0) {
            result = this.y.compareTo(point.y);
        }
        return result;
    }
}
