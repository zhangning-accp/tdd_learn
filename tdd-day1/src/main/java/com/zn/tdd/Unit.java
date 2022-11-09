package com.zn.tdd;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/10/11-11:09:06
 */
public enum Unit {

    /**
     *
     */
    MILLIMETER(1),
    /**
     *
     */
    CENTIMETER(10),
    /**
     *
     */
    DECIMETER(100),

    /**
     *
     */
    METER(1000);

    private int transitionRateMillimeter;

    Unit(int transitionRateMillimeter) {
        this.transitionRateMillimeter = transitionRateMillimeter;
    }

    public int getTransitionRateMillimeter() {
        return this.transitionRateMillimeter;
    }
}
