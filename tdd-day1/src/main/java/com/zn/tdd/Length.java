package com.zn.tdd;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learnb
 * @page:com.zn.tdd
 * @date:2022/10/11-10:58:44
 */
public class Length {
    private int amount;

    public Length(int amount, Unit unit) {
        this.amount = amount * unit.getTransitionRateMillimeter();
    }

    @Override
    public boolean equals(Object obj) {
        Length millimeter = (Length) obj;
        return millimeter.amount == this.amount;
    }
}
