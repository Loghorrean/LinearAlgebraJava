package com.loghorrean.algebra;

public class PointInSpace extends Point{
    protected Double z;

    public PointInSpace(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }
}
